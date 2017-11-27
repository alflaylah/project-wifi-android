package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.APPLICATION
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel
import org.greenrobot.eventbus.EventBus

/**
 * 権限ダイアログViewModel
 */
class ConfirmDialogViewModel : BaseDialogViewModel() {

  /**
   * PositiveButton押下
   */
  fun onPositiveButtonClicked() {
    EventBus.getDefault().post(CloseEvent(APPLICATION))
  }
}