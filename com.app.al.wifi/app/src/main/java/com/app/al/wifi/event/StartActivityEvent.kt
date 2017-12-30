package com.app.al.wifi.event

/**
 * 画面遷移イベント
 *
 * @param id 遷移先判定ID
 */
class StartActivityEvent(val id: Int) {

  companion object {
    // TODO
    // 画面遷移判別コード
    val OS_SETTING = 0    // OSのアプリケーション設定画面
    val APP_ETC = 1    // その他一覧画面
    val APP_LICENSE = 2   // ライセンス画面
  }
}