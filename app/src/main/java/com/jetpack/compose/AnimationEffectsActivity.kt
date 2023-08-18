package com.jetpack.compose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jetpack.compose.databinding.ActivityAnimationEffectsBinding

/**
 * AnimationEffectsActivity
 *
 * @author : zhen51.wang
 * @date : 2023/8/18/018
 */
class AnimationEffectsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationEffectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationEffectsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}