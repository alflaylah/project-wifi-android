package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.StartActivityEvent
import org.greenrobot.eventbus.EventBus

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
    EventBus.getDefault().post(StartActivityEvent(resId))
  }
}