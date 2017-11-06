package com.app.al.wifi.view.fragment.base

import com.app.al.wifi.MainApplication
import com.app.al.wifi.di.ApplicationComponent
import com.trello.rxlifecycle2.components.support.RxDialogFragment

/*
 * 基底Fragment
 */
open class BaseDialogFragment : RxDialogFragment() {

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent {
    return (context?.applicationContext as MainApplication).getApplicationComponent()
  }
}