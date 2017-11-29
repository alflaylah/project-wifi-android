package com.app.al.wifi.view.activity.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.app.al.wifi.MainApplication
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst.NavigationIconEventType
import com.app.al.wifi.const.ApplicationConst.NavigationIconEventType.RETURN
import com.app.al.wifi.di.ApplicationComponent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * 基底Activity
 */
open class BaseActivity : RxAppCompatActivity() {

  private var toolbar: Toolbar? = null

  @Inject
  protected lateinit var compositeDisposable: CompositeDisposable

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    getApplicationComponent().inject(this)
  }

  /**
   * onDestroy
   */
  override fun onDestroy() {
    super.onDestroy()
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
  }

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent = (applicationContext as MainApplication).getApplicationComponent()

  /**
   * ToolBar初期処理
   *
   * @param resId リソースID
   */
  protected fun initToolBar(@StringRes resId: Int) {
    toolbar = findViewById(R.id.toolbar)
    if (toolbar != null) {
      setToolbarTitle(resId)
      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)
      supportActionBar?.setHomeButtonEnabled(true)
    }
  }

  /**
   * ToolBar初期処理
   *
   * @param resId リソースID
   * @param eventType NavigationIconイベントタイプ
   */
  protected fun initToolBar(@StringRes resId: Int, eventType: NavigationIconEventType) {
    initToolBar(resId)
    setNavigationOnClickEvent(eventType)
  }

  /**
   * Toolbarタイトル設定
   *
   * @param resId リソースID
   */
  private fun setToolbarTitle(@StringRes resId: Int) {
    toolbar?.setTitle(resId)
  }

  /**
   * Toolbar NavigationIcon押下時イベント
   *
   * @param eventType NavigationIconイベントタイプ
   */
  private fun setNavigationOnClickEvent(eventType: NavigationIconEventType) {
    when (eventType) {
      RETURN -> {
        toolbar?.setNavigationOnClickListener({ onBackPressed() })
      }
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
   */
  protected fun replaceFragment(fragment: Fragment) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.fragment_container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }
}