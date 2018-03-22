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
   * @param ssid SSID
   * @param capabilities capabilities
   * @param password パスワード
   */
  fun connect(
    ssid: String,
    capabilities: String,
    password: String?
  ) {
    RxBusProvider.instance.post(WifiEvent(ssid, capabilities, password))
  }
}