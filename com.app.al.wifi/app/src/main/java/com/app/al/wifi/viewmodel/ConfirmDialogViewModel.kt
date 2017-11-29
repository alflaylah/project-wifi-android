package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.APPLICATION
import com.app.al.wifi.event.bus.RxBusProvider
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel

/**
 * 確認ダイアログViewModel
 */
class ConfirmDialogViewModel : BaseDialogViewModel() {

  /**
   * PositiveButton押下
   */
  fun onPositiveButtonClicked() {
    RxBusProvider.instance.post(CloseEvent(APPLICATION))
  }
}