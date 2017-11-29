package com.app.al.wifi.view.fragment.base

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.app.al.wifi.MainApplication
import com.app.al.wifi.di.ApplicationComponent
import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.DIALOG
import com.app.al.wifi.event.bus.RxBusProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * 基底Fragment
 */
open class BaseDialogFragment : DialogFragment() {

  @Inject
  protected lateinit var compositeDisposable: CompositeDisposable

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    init()
  }

  /**
   * onDestroy
   */
  override fun onDestroy() {
    super.onDestroy()
    if (!compositeDisposable.isDisposed) compositeDisposable.clear()
  }

  /**
   * 初期処理
   */
  private fun init() {
    getApplicationComponent().inject(this)
    initEvent()
  }

  /**
   * イベント初期処理
   */
  private fun initEvent() {
//    RxEventBus.stream(CloseEvent::class.java).subscribe({
//      onCloseDialogEvent(it)
//    })
    compositeDisposable.add(RxBusProvider.instance.toObservable(CloseEvent::class.java)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onCloseDialogEvent(it) }))
  }

  /**
   * ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  protected fun getApplicationComponent(): ApplicationComponent = (context?.applicationContext as MainApplication).getApplicationComponent()

  /**
   * EventBus ダイアログクローズ
   */
  private fun onCloseDialogEvent(event: CloseEvent) {
    when (event.closeType) {
      DIALOG -> {
        dismiss()
      }
      else -> {
      }
    }
  }
}