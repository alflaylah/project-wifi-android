package com.app.al.wifi.model

import com.app.al.wifi.event.WifiConnectEvent

import org.greenrobot.eventbus.EventBus

/**
 * WifiModel
 */
class WifiModel {

  /**
   * WIFI接続
   *
   * @param ssId SSID
   * @param capabilities capabilities
   */
  fun connect(ssId: String, capabilities: String) {
    EventBus.getDefault().post(WifiConnectEvent(ssId, capabilities))
  }
}