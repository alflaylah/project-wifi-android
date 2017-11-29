package com.app.al.wifi.viewmodel

import android.net.wifi.ScanResult
import java.io.Serializable

/**
 * Wifi一覧ViewModel
 *
 * @param scanResult アクセスポイント検索結果
 */
class WifiListViewModel(scanResult: ScanResult) : Serializable {
  val ssid: String = scanResult.SSID
  val capabilities: String = scanResult.capabilities
  val level: String = scanResult.level.toString()
}