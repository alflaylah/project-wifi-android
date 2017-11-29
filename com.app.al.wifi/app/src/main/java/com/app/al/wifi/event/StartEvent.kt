package com.app.al.wifi.event

import com.app.al.wifi.R

/**
 * EventBus 画面遷移イベント
 *
 * @param id 遷移先判定ID
 */
class StartEvent(val id: Int) {

  companion object {
    val APP_LICENSE = R.id.menu_license
    // OSのアプリケーション設定画面
    val OS_SETTING = 0
  }
}