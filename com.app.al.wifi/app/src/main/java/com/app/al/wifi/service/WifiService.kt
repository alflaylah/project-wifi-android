package com.app.al.wifi.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Wifiサービス
 */
class WifiService : Service() {

  /**
   * onBind
   *
   * @param intent
   */
  override fun onBind(intent: Intent?): IBinder? {
    return null
  }

  /**
   * onBind
   *
   * @param intent intent
   * @param flags flags
   * @param startId startId
   * @return サービス強制終了時の動作値
   */
  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    super.onStartCommand(intent, flags, startId)
    // サービス再起動
    return Service.START_STICKY
  }
}