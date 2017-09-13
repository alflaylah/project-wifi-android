package com.app.al.wifi.viewmodel

import android.net.wifi.ScanResult

/**
 * WIFI情報ViewModel
 *
 * @param scanResult WIFI検索結果
 */
class WifiListItemViewModel(scanResult: ScanResult) {
  val ssId: String = scanResult.SSID
  val bssId: String = scanResult.BSSID
  val capabilities: String = scanResult.capabilities
  val level: String = scanResult.level.toString()
  val frequency: String = scanResult.frequency.toString()
}
