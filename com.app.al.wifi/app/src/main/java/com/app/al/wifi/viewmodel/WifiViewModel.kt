package com.app.al.wifi.viewmodel

import android.net.wifi.ScanResult

/**
 * WifiViewModel
 *
 * @param scanResult アクセスポイント検索結果
 */
class WifiViewModel(scanResult: ScanResult) {
  val ssId: String = scanResult.SSID
  val bssId: String = scanResult.BSSID
  val capabilities: String = scanResult.capabilities
  val level: String = scanResult.level.toString()
  val frequency: String = scanResult.frequency.toString()
}
