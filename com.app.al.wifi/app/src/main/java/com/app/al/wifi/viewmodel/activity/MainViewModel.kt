package com.app.al.wifi.viewmodel.activity

import com.app.al.wifi.event.StartEvent
import com.app.al.wifi.event.bus.RxBusProvider

/**
 * MainViewModel
 */
class MainViewModel {

  /**
   * ドロワーレイアウトメニュー押下
   *
   * @param startCode 画面遷移判別コード
   */
  fun onNavigationItemSelected(startCode: Int) {
    RxBusProvider.instance.post(StartEvent(startCode))
  }
}