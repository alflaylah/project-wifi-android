package com.app.al.wifi.model

import android.content.Context
import android.widget.Toast
import com.app.al.wifi.util.PermissionUtils
import com.app.al.wifi.view.fragment.PermissionDialogFragment
import com.app.al.wifi.viewmodel.WifiViewModel

/**
 * Wifi一覧Model
 *
 * @param context Context
 */
class WifiListModel(private val context: Context) {

  /**
   * WIFI接続
   *
   * @param wifiListItemViewModel WifiViewModel
   */
  fun connect(wifiListItemViewModel: WifiViewModel) {
    Toast.makeText(context, "Clicked on $wifiListItemViewModel.getSsId", Toast.LENGTH_LONG).show()
//    WifiUtils.connect(context, wifiListItemViewModel.ssId, wifiListItemViewModel.capabilities, "")
  }
}