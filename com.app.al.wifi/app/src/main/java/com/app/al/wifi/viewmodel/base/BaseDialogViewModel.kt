package com.app.al.wifi.viewmodel.base

import com.app.al.wifi.event.CloseDialogEvent
import org.greenrobot.eventbus.EventBus

/**
 * 基底ダイアログViewModel
 */
open class BaseDialogViewModel {

  /**
   * キャンセルボタン押下
   */
  fun onCancelClicked() {
    EventBus.getDefault().post(CloseDialogEvent())
  }
}