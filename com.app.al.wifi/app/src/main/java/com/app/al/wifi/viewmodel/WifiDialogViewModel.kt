package com.app.al.wifi.viewmodel

import android.view.View
import com.app.al.wifi.event.CloseDialogEvent
import com.app.al.wifi.model.WifiModel
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel
import org.greenrobot.eventbus.EventBus

/**
 * WifiダイアログViewModel
 *
 * @param ssId SSID
 * @param capabilities capabilities
 */
class WifiDialogViewModel(val ssId: String, val capabilities: String) : BaseDialogViewModel() {

  private var wifiModel: WifiModel = WifiModel()

  /**
   * 接続ボタン押下
   */
  fun onConnectClicked(view: View) {
    // Wifi接続
    wifiModel.connect(ssId, capabilities)
    EventBus.getDefault().post(CloseDialogEvent())
  }
}