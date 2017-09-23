package com.app.al.wifi.viewmodel

import android.content.Context
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import com.app.al.wifi.model.WifiListModel


/**
 * Wifi一覧ViewModel
 */
class WifiListViewModel(context: Context) {

  private var wifiListModel: WifiListModel = WifiListModel(context)
  var isLoading = ObservableBoolean()

  /**
   * Wifi一覧押下時イベント
   */
  fun OnItemClick(wifiListItemViewModel: WifiViewModel) {
    // Wifi接続
    wifiListModel.connect(wifiListItemViewModel)
  }

  /**
   * Wifi一覧更新時イベント
   */
  fun onRefresh() {
    isLoading.set(false)
  }
}