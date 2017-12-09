package com.app.al.wifi.viewmodel.dialog.base

import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.DIALOG
import com.app.al.wifi.event.bus.RxBusProvider

/**
 * 基底ダイアログViewModel
 */
open class BaseDialogViewModel {

  /**
   * NegativeButton押下
   */
  fun onNegativeButtonClicked() {
    RxBusProvider.instance.post(CloseEvent(DIALOG))
  }
}