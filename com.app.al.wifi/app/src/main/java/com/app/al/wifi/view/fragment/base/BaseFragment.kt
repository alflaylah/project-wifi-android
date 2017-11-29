package com.app.al.wifi.view.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.app.al.wifi.MainApplication
import com.app.al.wifi.di.ApplicationComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * 基底Fragment
 */
open class BaseFragment : Fragment() {

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
  protected fun getApplicationComponent(): ApplicationComponent = (context?.applicationContext as MainApplication).getApplicationComponent()
}