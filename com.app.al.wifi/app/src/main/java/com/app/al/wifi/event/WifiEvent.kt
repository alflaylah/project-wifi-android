package com.app.al.wifi.event

/**
 * EventBus Wifi関連イベント
 */
class WifiEvent() {

  var ssid: String? = null
  var capabilities: String? = null
  var password: String? = null
  var message: String? = null

  /**
   * コンストラクタ
   *
   * @param ssid ssid
   * @param capabilities capabilities
   * @param password パスワード
   */
  constructor(ssid: String?, capabilities: String?, password: String?) : this() {
    this.ssid = ssid
    this.capabilities = capabilities
    this.password = password
  }

  /**
   * コンストラクタ
   *
   * @param message 接続状態を表すメッセージ
   */
  constructor(message: String?) : this() {
    this.message = message
  }
}