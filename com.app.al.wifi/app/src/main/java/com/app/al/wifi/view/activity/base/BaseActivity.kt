package com.app.al.wifi.view.activity.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.app.al.wifi.MainApplication
import com.app.al.wifi.R
import com.app.al.wifi.di.ApplicationComponent
import com.app.al.wifi.view.activity.WebActivity
import com.app.al.wifi.view.fragment.EtcListFragment
import com.app.al.wifi.view.fragment.WifiListFragment

/**
 * 基底Activity
 */
open class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

  private var toolbar: Toolbar? = null

  /**
   * onBackStackChanged
   */
  override fun onBackStackChanged() {
    Log.d(this::class.simpleName, "onBackStackChanged : " + supportFragmentManager.findFragmentById(R.id.fragment_container))
    // タイトル設定
    setTitle(supportFragmentManager.findFragmentById(R.id.fragment_container))
  }

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent = (applicationContext as MainApplication).getApplicationComponent()

  /**
   * 初期処理
   */
  open fun init() {
    initToolBar()
  }

  /**
   * ToolBar初期処理
   */
  private fun initToolBar() {
    toolbar = findViewById(R.id.toolbar)
    if (toolbar != null) {
      setNavigationOnClickEvent()
    }
  }

  /**
   * タイトル設定
   *
   * @param fragment フラグメント
   */
  private fun setTitle(fragment: Fragment) {
    when (fragment) {
      is WifiListFragment -> toolbar?.setTitle(R.string.title_main)
      is EtcListFragment -> toolbar?.setTitle(R.string.title_etc)
    }
  }


  /**
   * Toolbar NavigationIcon押下時イベント
   */
  private fun setNavigationOnClickEvent() {
    when (this) {
      is WebActivity -> toolbar?.setNavigationOnClickListener({ onBackPressed() })
    }
  }

  /**
   * ToolBar返却
   *
   * @return Toolbar
   */
  protected fun getToolbar(): Toolbar? = toolbar

  /**
   * フラグメント置換
   *
   * @param fragment 置換フラグメント
   */
  protected fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.addOnBackStackChangedListener(this)
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    if (supportFragmentManager.findFragmentByTag(fragment::class.java.name) != null) {
      fragmentTransaction.show(fragment)
    } else {
      fragmentTransaction.replace(R.id.fragment_container, fragment, fragment::class.java.name)
      fragmentTransaction.addToBackStack(null)
    }
    fragmentTransaction.commit()
  }
}