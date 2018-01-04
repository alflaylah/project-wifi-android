package com.app.al.wifi.const

import android.Manifest.permission

/**
 * 定数
 */
object ApplicationConst {

  // 汎用
  val EMPTY = ""
  val DOUBLE_QUOTE = "\""

  // リソースタイプ
  val MIPMAP: String = "mipmap"

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

  // Wifi Level
  enum class WifiLevel(val status: String, val resourceName: String) {
    LEVEL_0("不安定", "ic_signal_wifi_level_0"),
    LEVEL_1("やや不安定", "ic_signal_wifi_level_1"),
    LEVEL_2("普通", "ic_signal_wifi_level_2"),
    LEVEL_3("やや安定", "ic_signal_wifi_level_3"),
    LEVEL_4("安定", "ic_signal_wifi_level_4")
  }

  // Wifi 除外SSID
  val IGNORE_01: String = "0x"
  val IGNORE_02: String = "<unknown ssid>"
}