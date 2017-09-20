package com.app.al.wifi.viewmodel

import android.content.Context
import com.app.al.wifi.model.WifiListModel

/**
 * WIFI情報一覧ViewModel
 */
class WifiListViewModel constructor(context: Context) {

  private var wifiListModel: WifiListModel = WifiListModel(context)

  /**
   * WIFI情報一覧イベント設定
   */
  fun OnItemClick(wifiListItemViewModel: WifiListItemViewModel) {
    // WIFI接続
    wifiListModel.connect(wifiListItemViewModel)
  }
}