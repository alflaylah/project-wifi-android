package com.app.al.wifi.model

import com.app.al.wifi.event.WifiEvent

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
   * @param password パスワード
   */
  fun connect(ssId: String, capabilities: String, password: String) {
    EventBus.getDefault().post(WifiEvent(ssId, capabilities, password))
  }
}