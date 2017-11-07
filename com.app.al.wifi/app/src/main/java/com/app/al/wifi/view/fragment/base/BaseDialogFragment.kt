package com.app.al.wifi.view.fragment.base

import com.app.al.wifi.MainApplication
import com.app.al.wifi.di.ApplicationComponent
import com.app.al.wifi.event.CloseDialogEvent
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/*
 * 基底Fragment
 */
open class BaseDialogFragment : RxDialogFragment() {

  /**
   * onStart
   */
  override fun onStart() {
    super.onStart()
    EventBus.getDefault().register(this)
  }

  /**
   * onStop
   */
  override fun onStop() {
    super.onStop()
    EventBus.getDefault().unregister(this)
  }

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent {
    return (context?.applicationContext as MainApplication).getApplicationComponent()
  }

  /**
   * EventBus ダイアログクローズ
   */
  @Subscribe(threadMode = ThreadMode.POSTING)
  protected fun onCloseDialogEvent(event: CloseDialogEvent) {
    dismiss()
  }
}