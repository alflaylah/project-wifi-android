package com.app.al.wifi.const

import android.Manifest.permission

/**
 * 定数
 */
object ApplicationConst {
  // 汎用
  val EMPTY = ""
  val DOUBLE_QUOTE = "\""

  // パスワード
  val REGEX_WEP = "[0-9A-Fa-f]*"
  val REGEX_PSK = "[0-9A-Fa-f]{64}"

  // Bundle
  val BUNDLE_URL = "BUNDLE_URL"
  val BUNDLE_OBJECT = "BUNDLE_OBJECT"

  // 権限
  val PERMISSIONS = arrayOf(
      permission.ACCESS_NETWORK_STATE,
      permission.ACCESS_WIFI_STATE,
      permission.ACCESS_COARSE_LOCATION,
      permission.CHANGE_WIFI_STATE
  )

  // Wifiセキュリティタイプ
  enum class WifiSecurityType(val type: String) {
    NONE("NONE"),
    WEP("WEP"),
    PSK("PSK"),
  }

  // Toolbar NavigationIconイベントタイプ
  enum class NavigationIconEventType(val type: String) {
    RETURN("RETURN")
  }
}