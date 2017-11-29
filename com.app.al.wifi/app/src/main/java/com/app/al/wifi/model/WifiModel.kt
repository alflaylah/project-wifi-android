package com.app.al.wifi.model

import com.app.al.wifi.event.WifiEvent
import com.app.al.wifi.event.bus.RxBusProvider

/**
 * WifiModel
 */
class WifiModel {

  /**
   * WIFI接続
   *
   * @param ssId SSID
   * @param capabilities capabilities
   * @param password パスワード
   */
  fun connect(ssId: String, capabilities: String, password: String) {
    RxBusProvider.instance.post(WifiEvent(ssId, capabilities, password))
  }
}