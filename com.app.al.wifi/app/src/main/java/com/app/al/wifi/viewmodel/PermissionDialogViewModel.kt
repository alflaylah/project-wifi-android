package com.app.al.wifi.viewmodel

import com.app.al.wifi.event.StartEvent
import com.app.al.wifi.event.bus.RxBusProvider
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel

/**
 * 権限ダイアログViewModel
 */
class PermissionDialogViewModel : BaseDialogViewModel() {

  /**
   * PositiveButton押下
   */
  fun onPositiveButtonClicked() {
    RxBusProvider.instance.post(StartEvent.OS_SETTING)
  }
}