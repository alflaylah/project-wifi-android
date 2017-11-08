package com.app.al.wifi.viewmodel

import android.databinding.ObservableBoolean
import android.net.wifi.ScanResult
import java.io.Serializable

/**
 * Wifi一覧ViewModel
 *
 * @param scanResult アクセスポイント検索結果
 */
class WifiListViewModel(scanResult: ScanResult) : Serializable {

  val ssId: String = scanResult.SSID
  val bssId: String = scanResult.BSSID
  val capabilities: String = scanResult.capabilities
  val level: String = scanResult.level.toString()
  val frequency: String = scanResult.frequency.toString()
  var isLoading = ObservableBoolean()

  /**
   * Wifi一覧更新時イベント
   */
  fun onRefresh() {
    isLoading.set(false)
  }
}