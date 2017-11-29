package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.APPLICATION
import com.app.al.wifi.event.RxBus
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel

/**
 * 確認ダイアログViewModel
 */
class ConfirmDialogViewModel : BaseDialogViewModel() {

  /**
   * PositiveButton押下
   */
  fun onPositiveButtonClicked() {
    RxBus.send(CloseEvent(APPLICATION))
  }
}