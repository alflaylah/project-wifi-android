package com.app.al.wifi.viewmodel

import android.content.Context
import com.app.al.wifi.model.WifiListModel

/**
 * Wifi一覧ViewModel
 */
class WifiListViewModel constructor(context: Context) {

  private var wifiListModel: WifiListModel = WifiListModel(context)

  /**
   * Wifi一覧イベント設定
   */
  fun OnItemClick(wifiListItemViewModel: WifiViewModel) {
    // Wifi接続
    wifiListModel.connect(wifiListItemViewModel)
  }
}