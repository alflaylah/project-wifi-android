package com.app.al.wifi.const

import android.Manifest.permission

/**
 * 定数
 */
object ApplicationConst {

  // 汎用
  val EMPTY = ""
  val DOUBLE_QUOTE = "\""

  // アプリケーション終了までの猶予(ミリ秒)
  val FINISH_CONFIRM = 4000

  // パスワード
  val REGEX_WEP = "[0-9A-Fa-f]*"
  val REGEX_PSK = "[0-9A-Fa-f]{64}"

  // Bundle
  val BUNDLE_URL = "BUNDLE_URL"
  val BUNDLE_OBJECT = "BUNDLE_OBJECT"

  // REQUEST_CODE
  val REQUEST_PERMISSION = 20
  val REQUEST_LOCATION = 20

  // 許可が必要な権限
  val PERMISSIONS = arrayOf(
      permission.ACCESS_NETWORK_STATE,
      permission.ACCESS_WIFI_STATE,
      permission.ACCESS_COARSE_LOCATION,
      permission.CHANGE_WIFI_STATE
  )

  // Wifi Securityタイプ
  enum class WifiSecurityType(val type: String) {
    NONE("NONE"),
    WEP("WEP"),
    PSK("PSK")
  }

  // Wifi 除外SSID
  val IGNORE_01: String = "0x"
  val IGNORE_02: String = "<unknown ssid>"

  // Toolbar NavigationIconイベントタイプ
  enum class NavigationIconEventType(val type: String) {
    RETURN("RETURN")
  }
}