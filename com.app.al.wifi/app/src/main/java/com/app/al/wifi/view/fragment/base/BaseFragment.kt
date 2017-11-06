package com.app.al.wifi.view.fragment.base

import com.app.al.wifi.MainApplication
import com.app.al.wifi.di.ApplicationComponent
import com.trello.rxlifecycle2.components.support.RxFragment

/*
 * 基底Fragment
 */
open class BaseFragment : RxFragment() {

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent {
    return (context?.applicationContext as MainApplication).getApplicationComponent()
  }
}