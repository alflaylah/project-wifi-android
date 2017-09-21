package com.app.al.wifi.view.activity.base

import android.support.v4.app.Fragment
import com.app.al.wifi.MainApplication
import com.app.al.wifi.R.id
import com.app.al.wifi.di.ApplicationComponent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * 基底Activity
 */
open class BaseActivity : RxAppCompatActivity() {

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent {
    return (applicationContext as MainApplication).getApplicationComponent()
  }

  /**
   * フラグメント置換処理
   */
  protected fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(id.fragment_container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }
}