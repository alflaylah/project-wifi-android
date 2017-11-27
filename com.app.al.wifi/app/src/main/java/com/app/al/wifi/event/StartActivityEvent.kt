package com.app.al.wifi.event

/**
 * EventBus 画面遷移イベント
 *
 * @param id 遷移先判定ID
 */
class StartActivityEvent(val id: Int) {

  companion object {
    // OSのアプリケーション設定画面
    val OS_SETTING = 0
  }
}