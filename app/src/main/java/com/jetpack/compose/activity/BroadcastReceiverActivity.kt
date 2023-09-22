package com.jetpack.compose.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.compose.base.BuildHeader
import com.jetpack.compose.base.BuildPage
import com.jetpack.compose.data.HeaderData

/**
 * BroadcastReceiverActivity
 *
 * @author : zhen51.wang
 * @date : 2023/9/22/022
 */
class BroadcastReceiverActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildPage(title = "实时获取电池信息") {
                BuildContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuildContent() {
    var technology by remember { mutableStateOf("") }
    var health by remember { mutableStateOf(BatteryManager.BATTERY_HEALTH_UNKNOWN) }
    var status by remember { mutableStateOf(BatteryManager.BATTERY_STATUS_UNKNOWN) }
    var plugged by remember { mutableStateOf(BatteryManager.BATTERY_PLUGGED_USB) }
    var chargeCounter by remember { mutableStateOf(0) }
    var level by remember { mutableStateOf(0) }
    var scale by remember { mutableStateOf(100) }
    var temperature by remember { mutableStateOf(0) }
    var voltage by remember { mutableStateOf(0) }
    var batteryLow by remember { mutableStateOf(false) }

//    technology : Li-ion
//    health : 2 健康：Good/Unknown/Dead
//    status : 3 状态：未充电/正在充电
//    plugged : 0 充电方式
//    charge_counter : 3604056 剩余电量 单位（mAH）微安小时
//    level : 99 剩余电量 不是绝对值，需要除以scale获取百分比
//    scale : 100 充满电量
//    temperature : 320 电池温度，除以10
//    voltage : 4325 电池电压：mv
//    battery_low : false

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        BuildHeader(
            HeaderData(
                text = "利用 LocalContext 来使用当前上下文以及 rememberUpdatedState 和 DisposableEffect 附带效应"
            )
        )
        BatteryItem("电池技术：$technology")
        BatteryItem("电池健康：${formatHealth(health)}")
        BatteryItem("电池温度：${temperature / 10} 度")
        BatteryItem("电池电压：$voltage 伏特")
        BatteryItem("充电状态：${formatStatus(status)}")
        BatteryItem("充电方式：${formatPlugged(plugged)}")
        BatteryItem("剩余电量：${(chargeCounter / 1000)} 毫安时")
        BatteryItem("剩余电量：${(level * 100f / scale).toInt()}%")
        BatteryItem("低电量：${if (batteryLow) "是" else "否"}")

        SystemBroadcastReceiver(action = Intent.ACTION_BATTERY_CHANGED) { intent ->
            intent?.extras?.let { extras ->
                technology = extras.getString(BatteryManager.EXTRA_TECHNOLOGY) ?: ""
                health = extras.getInt(BatteryManager.EXTRA_HEALTH)
                status = extras.getInt(BatteryManager.EXTRA_STATUS)
                plugged = extras.getInt(BatteryManager.EXTRA_PLUGGED)
                chargeCounter = extras.getInt("charge_counter")
                level = extras.getInt(BatteryManager.EXTRA_LEVEL)
                scale = extras.getInt(BatteryManager.EXTRA_SCALE)
                temperature = extras.getInt(BatteryManager.EXTRA_TEMPERATURE)
                voltage = extras.getInt(BatteryManager.EXTRA_VOLTAGE)
                batteryLow = extras.getBoolean("battery_low")
            }
            println("on event: ${intent?.action}")
        }
    }
}

fun formatHealth(health: Int) = when (health) {
    BatteryManager.BATTERY_HEALTH_GOOD -> "良好"
    BatteryManager.BATTERY_HEALTH_DEAD -> "死机"
    BatteryManager.BATTERY_HEALTH_COLD -> "低温"
    BatteryManager.BATTERY_HEALTH_OVERHEAT -> "过热"
    BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "电压过载"
    else -> "未知"
}

fun formatPlugged(plugged: Int) = when (plugged) {
    BatteryManager.BATTERY_PLUGGED_AC -> "充电器"
    BatteryManager.BATTERY_PLUGGED_USB -> "USB插入"
    BatteryManager.BATTERY_PLUGGED_DOCK -> "DOCK"
    BatteryManager.BATTERY_PLUGGED_WIRELESS -> "无线充电"
    else -> "未充电"
}

fun formatStatus(status: Int) = when (status) {
    BatteryManager.BATTERY_STATUS_FULL -> "已充满"
    BatteryManager.BATTERY_STATUS_CHARGING -> "充电中"
    BatteryManager.BATTERY_STATUS_DISCHARGING -> "放电中"
    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "未充电"
    else -> "未知"
}

@Composable
fun SystemBroadcastReceiver(action: String, event: (intent: Intent?) -> Unit) {
    val context = LocalContext.current
    DisposableEffect(context, action) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {
                event(intent)
            }
        }
        context.registerReceiver(receiver, IntentFilter(action))
        println("registerReceiver")
        onDispose {
            context.unregisterReceiver(receiver)
            println("unregisterReceiver")
        }
    }
}

@Composable
fun BatteryItem(msg: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(20.dp)
    ) {
        Text(
            text = msg,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}