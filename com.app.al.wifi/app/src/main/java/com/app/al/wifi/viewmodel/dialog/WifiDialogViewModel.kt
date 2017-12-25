package com.app.al.wifi.viewmodel.dialog

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import com.app.al.wifi.R
import com.app.al.wifi.model.WifiModel
import com.app.al.wifi.util.RxUtils
import com.app.al.wifi.util.WifiUtils
import com.app.al.wifi.viewmodel.dialog.base.BaseDialogViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * WifiダイアログViewModel
 *
 * @param context コンテキスト
 * @param ssid SSID
 * @param capabilities capabilities
 */
class WifiDialogViewModel(val context: Context?, val ssid: String, private val capabilities: String) : BaseDialogViewModel(), Disposable {

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
   * パスワード表示判定
   *
   * @return View表示ステータス
   */
  fun getPasswordVisibility(): Int {
    // 利用履歴のあるWiFiはパスワードを入力する必要がないので非表示
    if (WifiUtils.isAccessPointConnecting(context, ssid) || WifiUtils.isAccessPointHistory(context, ssid)) {
      return View.GONE
    }
    return View.VISIBLE
  }

  /**
   * 実行ボタン表示文言返却
   *
   * @return 表示文言
   */
  fun getDoButtonText(): String? {
    // 利用中のWiFiの場合、ボタンの文言を「保存」にする
    if (WifiUtils.isAccessPointConnecting(context, ssid)) {
      return context?.getString(R.string.save)
    }
    return context?.getString(R.string.connect)
  }

  /**
   * 接続ボタン押下
   */
  fun onConnectClicked() {
    // Wifi接続
    wifiModel.connect(ssid, capabilities, password.get())
  }
}