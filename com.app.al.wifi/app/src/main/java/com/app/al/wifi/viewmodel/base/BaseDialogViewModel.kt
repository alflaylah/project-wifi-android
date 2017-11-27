package com.app.al.wifi.viewmodel.base

import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.DIALOG
import org.greenrobot.eventbus.EventBus

/**
 * 基底ダイアログViewModel
 */
open class BaseDialogViewModel {

  /**
   * NegativeButton押下
   */
  fun onNegativeButtonClicked() {
    EventBus.getDefault().post(CloseEvent(DIALOG))
  }
}