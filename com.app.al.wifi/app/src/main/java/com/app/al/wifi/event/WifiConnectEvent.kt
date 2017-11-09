package com.app.al.wifi.event

/**
 * EventBus Wifi接続イベント
 *
 * @param ssId ssId
 * @param capabilities capabilities
 * @param password パスワード
 */
class WifiConnectEvent(val ssId: String, val capabilities: String, val password: String)