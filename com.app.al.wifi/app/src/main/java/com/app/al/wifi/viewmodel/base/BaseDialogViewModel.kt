package com.app.al.wifi.viewmodel.base

import android.view.View
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

  /**
   * キャンセルボタン押下
   *
   * @param view view
   */
  fun onCancelClicked(view: View) {
    EventBus.getDefault().post(CloseDialogEvent())
  }
}