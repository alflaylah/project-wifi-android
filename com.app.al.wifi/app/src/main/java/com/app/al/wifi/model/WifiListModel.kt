package com.app.al.wifi.model

import android.content.Context
import android.widget.Toast
import com.app.al.wifi.viewmodel.WifiListItemViewModel

/**
 * WIFI情報一覧Model
 *
 * @param context Context
 */
class WifiListModel(private val context: Context) {

  /**
   * WIFI接続
   *
   * @param wifiListItemViewModel WIFI情報ViewModel
   */
  fun connect(wifiListItemViewModel: WifiListItemViewModel) {
    Toast.makeText(context, "Clicked on $wifiListItemViewModel.getSsId", Toast.LENGTH_LONG).show()
    // TODO
//    WifiUtils.connect(context, wifiListItemViewModel.ssId, wifiListItemViewModel.capabilities, "")
  }
}