package com.app.al.wifi.service

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log

/**
 * 歩数センサーサービス
 */
class SensorService : Service(), SensorEventListener {

  private var sensorManager: SensorManager? = null
  private var sensor: Sensor? = null
  private var sensorThread: HandlerThread? = null

  /**
   * onCreate
   */
  override fun onCreate() {
    sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
    sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    sensorThread = HandlerThread("PedometerServiceThread")
    sensorThread!!.start()
    sensorManager!!.registerListener(
        this, sensor, SensorManager.SENSOR_DELAY_FASTEST,
        Handler(sensorThread!!.looper)
    )
  }

  /**
   * onDestroy
   */
  override fun onDestroy() {
    sensorThread!!.looper.quit()
    sensorManager!!.unregisterListener(this)
  }

  override fun onStartCommand(
    intent: Intent,
    flags: Int,
    startId: Int
  ): Int {
    return START_STICKY
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  override fun onSensorChanged(event: SensorEvent) {
    val value = event.values[0].toInt()
    // 歩行を検知したときの処理
    val sensor = event.sensor
    val values = event.values
    //TYPE_STEP_COUNTER
    if (sensor.type == Sensor.TYPE_STEP_COUNTER) {
      // sensor からの値を取得するなどの処理を行う
      Log.d("type_step_counter", values[0].toString())
    }
  }

  override fun onAccuracyChanged(
    sensor: Sensor,
    accuracy: Int
  ) {
  }
}