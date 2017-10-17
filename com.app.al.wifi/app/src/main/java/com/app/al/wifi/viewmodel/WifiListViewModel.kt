package com.app.al.wifi.viewmodel

import android.databinding.ObservableBoolean
import android.net.wifi.ScanResult
import com.app.al.wifi.model.WifiModel
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

  private var wifiModel: WifiModel = WifiModel()

  /**
   * Wifi一覧押下時イベント
   */
  fun onItemClick() {
    // Wifi接続
    wifiModel.connect(ssId, capabilities)
  }

  /**
   * Wifi一覧更新時イベント
   */
  fun onRefresh() {
    isLoading.set(false)
  }
}