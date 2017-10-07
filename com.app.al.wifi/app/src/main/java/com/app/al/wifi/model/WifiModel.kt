package com.app.al.wifi.model

import android.content.Context
import com.app.al.wifi.util.WifiUtils
import com.app.al.wifi.viewmodel.WifiViewModel

/**
 * WifiModel
 *
 * @param context Context
 */
class WifiModel(private val context: Context) {

  /**
   * WIFI接続
   *
   * @param wifiListItemViewModel WifiViewModel
   */
  fun connect(wifiListItemViewModel: WifiViewModel) {
    WifiUtils.connect(context, wifiListItemViewModel.ssId, wifiListItemViewModel.capabilities, "")
  }
}