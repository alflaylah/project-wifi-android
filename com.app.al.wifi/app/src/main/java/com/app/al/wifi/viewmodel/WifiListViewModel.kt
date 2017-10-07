package com.app.al.wifi.viewmodel

import android.content.Context
import android.databinding.ObservableBoolean
import com.app.al.wifi.model.WifiModel

/**
 * Wifi一覧ViewModel
 *
 * @param context コンテキスト
 */
class WifiListViewModel(context: Context) {

  private var wifiModel: WifiModel = WifiModel(context)
  var isLoading = ObservableBoolean()

  /**
   * Wifi一覧押下時イベント
   *
   * @param wifiViewModel WifiViewModel
   */
  fun OnItemClicked(wifiViewModel: WifiViewModel) {
    // Wifi接続
    wifiModel.connect(wifiViewModel)
  }

  /**
   * Wifi一覧更新時イベント
   */
  fun onRefresh() {
    isLoading.set(false)
  }
}