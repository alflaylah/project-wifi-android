package com.app.al.wifi.viewmodel.dialog

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.app.al.wifi.R
import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.DIALOG
import com.app.al.wifi.event.bus.RxBusProvider
import com.app.al.wifi.model.WifiModel
import com.app.al.wifi.util.RxUtils
import com.app.al.wifi.util.SharedPreferenceUtils
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
  var selectedPosition = ObservableInt(0)
  var password: ObservableField<String> = ObservableField()
  var isDoButtonEnabled = ObservableBoolean(false)

  init {
    if (getPasswordVisibility() == View.GONE) {
      // パスワードが表示されていない場合、接続/保存ボタンは常に押下させる
      isDoButtonEnabled = ObservableBoolean(true)
    } else {
      // パスワード未入力時、接続ボタンは押下させない
      compositeDisposable.add(RxUtils.toFlowable(password).subscribe({ isDoButtonEnabled.set(it.isNotEmpty()) }))
    }
    // 電波強度の位置を設定
    selectedPosition.set(SharedPreferenceUtils.readInt(context, ssid))
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
   * 接続・保存ボタン押下
   */
  fun onDoButtonClicked() {
    // 電波強度の位置を保存する
    SharedPreferenceUtils.saveInt(context, ssid, selectedPosition.get())
    // 利用していないWifiなら接続を実施する
    val password: String? = password.get()
    if (!WifiUtils.isAccessPointConnecting(context, ssid)) {
      wifiModel.connect(ssid, capabilities, password)
    }
    // ダイアログ終了
    RxBusProvider.instance.post(CloseEvent(DIALOG))
  }
}