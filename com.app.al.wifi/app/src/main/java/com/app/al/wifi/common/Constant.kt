package com.app.al.wifi.common

import android.Manifest.permission

/**
 * 定数クラス
 */
object Constant {

  // 権限
  val PERMISSIONS = arrayOf(
      permission.ACCESS_NETWORK_STATE,
      permission.ACCESS_WIFI_STATE,
      permission.ACCESS_COARSE_LOCATION,
      permission.CHANGE_WIFI_STATE
  )

  // WIFIセキュリティ
  enum class SEQURITY_TYPE {
    SECURITY_NONE,
    SECURITY_WEP,
    SECURITY_PSK,
    SECURITY_EAP
  }
}