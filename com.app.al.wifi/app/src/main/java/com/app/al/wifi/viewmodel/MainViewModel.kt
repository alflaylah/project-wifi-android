package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.StartEvent
import com.app.al.wifi.event.bus.RxBusProvider

/**
 * MainViewModel
 */
class MainViewModel {

  /**
   * ドロワーレイアウトメニュー押下
   *
   * @param resId リソースID
   */
  fun onNavigationItemSelected(resId: Int) {
    RxBusProvider.instance.post(StartEvent(resId))
  }
}