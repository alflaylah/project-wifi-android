package com.app.al.wifi.const

import android.Manifest.permission

/**
 * 定数
 */
object ApplicationConst {

  // 汎用
  const val EMPTY = ""
  const val DOUBLE_QUOTE = "\""

  // リソースタイプ
  const val MIPMAP: String = "mipmap"

  // アプリケーション終了までの猶予(ミリ秒)
  const val FINISH_CONFIRM = 4000

  // パスワード
  const val REGEX_WEP = "[0-9A-Fa-f]*"
  const val REGEX_PSK = "[0-9A-Fa-f]{64}"

  // Bundle
  const val BUNDLE_URL = "BUNDLE_URL"
  const val BUNDLE_OBJECT = "BUNDLE_OBJECT"

  // Shared Preferences
  const val SHARED_PREFERENCES_NAME = "WOONEKA_WIFI"

  // Wifi 除外SSID
  const val IGNORE_01: String = "0x"
  const val IGNORE_02: String = "<unknown ssid>"

  // REQUEST_CODE
  const val REQUEST_PERMISSION = 20
  const val REQUEST_LOCATION = 20

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
  enum class WifiLevel(
    val status: String,
    val resourceName: String
  ) {
    LEVEL_0("不安定", "ic_signal_wifi_level_0"),
    LEVEL_1("やや不安定", "ic_signal_wifi_level_1"),
    LEVEL_2("普通", "ic_signal_wifi_level_2"),
    LEVEL_3("やや安定", "ic_signal_wifi_level_3"),
    LEVEL_4("安定", "ic_signal_wifi_level_4")
  }
}