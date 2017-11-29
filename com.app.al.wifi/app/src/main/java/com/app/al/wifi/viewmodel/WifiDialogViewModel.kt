package com.app.al.wifi.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.app.al.wifi.model.WifiModel
import com.app.al.wifi.util.RxUtils
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * WifiダイアログViewModel
 *
 * @param ssid SSID
 * @param capabilities capabilities
 */
class WifiDialogViewModel(val ssid: String, private val capabilities: String) : BaseDialogViewModel(), Disposable {

  private var wifiModel: WifiModel = WifiModel()
  private var compositeDisposable = CompositeDisposable()
  var password: ObservableField<String> = ObservableField()
  var connectEnabled = ObservableBoolean(false)

  init {
    // パスワード未入力時、接続ボタンは押させない
    compositeDisposable.add(RxUtils.toFlowable(password).subscribe({ connectEnabled.set(!it.isNotEmpty()) }))
  }

  /**
   * 破棄
   */
  override fun dispose() {
    compositeDisposable.dispose()
  }

  /**
   * 破棄判定
   *
   * @return true：破棄済み false：破棄まだ
   */
  override fun isDisposed(): Boolean = compositeDisposable.isDisposed

  /**
   * 接続ボタン押下
   */
  fun onConnectClicked() {
    // Wifi接続
    wifiModel.connect(ssid, capabilities, password.get())
  }
}