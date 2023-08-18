package com.jetpack.compose

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * DynamicEffectsView
 *
 * @author : zhen51.wang
 * @date : 2023/8/18/018
 */
class DynamicEffectsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        const val DURATION = 1500L
    }

    private val defaultSize = dip2px(150f)
    private var width = 0
    private var height = 0
    private val srcRectF = RectF()

    private var leftCircleColor = Color.TRANSPARENT
    private val leftCircleColors = mapOf(
        Color.parseColor("#F5D976") to Color.parseColor("#F57676"),
        Color.parseColor("#F57676") to Color.parseColor("#CD76F5"),
        Color.parseColor("#CD76F5") to Color.parseColor("#F5D976"),
    )
    private var rightCircleColor = Color.TRANSPARENT
    private val rightCircleColors = mapOf(
        Color.parseColor("#AC85FF") to Color.parseColor("#85D3FF"),
        Color.parseColor("#85D3FF") to Color.parseColor("#F5D976"),
        Color.parseColor("#F5D976") to Color.parseColor("#AC85FF"),
    )

    private val leftCirclePaint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val rightCirclePaint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }
    private val bitmapPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        alpha = (255 * 0.5).toInt()
        isFilterBitmap = true
    }

    private var leftAnimatorSet = AnimatorSet().apply {
        playSequentially(mutableListOf<Animator>().apply {
            for (entry in leftCircleColors.entries) {
                add(ValueAnimator.ofFloat(0f, 1f).apply {
                    addUpdateListener {
                        leftCircleColor =
                            transformColor(it.animatedFraction, entry.key, entry.value)
                        invalidate()
                    }
                    duration = DURATION
                })
            }
        })
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animation.start()
            }
        })
        start()
    }
    private val rightAnimatorSet = AnimatorSet().apply {
        playSequentially(
            mutableListOf<Animator>().apply {
                for (entry in rightCircleColors.entries) {
                    add(ValueAnimator.ofFloat(0f, 1f).apply {
                        addUpdateListener {
                            rightCircleColor =
                                transformColor(it.animatedFraction, entry.key, entry.value)
                            invalidate()
                        }
                        duration = DURATION
                    })
                }
            })
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                animation.start()
            }
        })
        start()
    }

    private val layerBitmap = BitmapFactory.decodeResource(resources, R.mipmap.middle_layer)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
        srcRectF.set(0f, 0f, w * 1f, h * 1f)
    }

    override fun onDraw(canvas: Canvas) {
        drawCircles(canvas)
        drawMiddleLayer(canvas)
    }

    private fun drawMiddleLayer(canvas: Canvas) {
        canvas.drawBitmap(layerBitmap, null, srcRectF, bitmapPaint)
    }

    private fun drawCircles(canvas: Canvas) {
        leftCirclePaint.color = leftCircleColor
        canvas.drawCircle(width * 2f / 5, height * 2f / 3, dip2px(160f).toFloat(), leftCirclePaint)
        rightCirclePaint.color = rightCircleColor
        canvas.drawCircle(width * 2f / 3, height * 2f / 5, dip2px(150f).toFloat(), rightCirclePaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec))
    }

    private fun measureSize(spec: Int): Int {
        val mode = MeasureSpec.getMode(spec)
        val size = MeasureSpec.getSize(spec)
        val value = when (mode) {
            MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> defaultSize.coerceAtMost(size)
            else -> defaultSize
        }
        return MeasureSpec.makeMeasureSpec(value, mode)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        leftAnimatorSet.cancel()
        rightAnimatorSet.cancel()
    }

    /**
     * transform colors
     *
     * @param progress  0~1
     * @param fromColor ARGB
     * @param toColor   ARGB
     * @return ARGB
     */
    private fun transformColor(progress: Float, fromColor: Int, toColor: Int): Int {
        val oa = fromColor shr 24 and 0xFF
        val or = fromColor shr 16 and 0xFF
        val og = fromColor shr 8 and 0xFF
        val ob = fromColor and 0xFF
        val na = toColor shr 24 and 0xFF
        val nr = toColor shr 16 and 0xFF
        val ng = toColor shr 8 and 0xFF
        val nb = toColor and 0xFF
        val aGap = ((na - oa).toFloat() * progress).toInt()
        val rGap = ((nr - or).toFloat() * progress).toInt()
        val gGap = ((ng - og).toFloat() * progress).toInt()
        val bGap = ((nb - ob).toFloat() * progress).toInt()
        return oa + aGap shl 24 or (or + rGap shl 16) or (og + gGap shl 8) or ob + bGap
    }

    private fun dip2px(dipValue: Float) =
        (dipValue * context.resources.displayMetrics.density + 0.5f).toInt()
}