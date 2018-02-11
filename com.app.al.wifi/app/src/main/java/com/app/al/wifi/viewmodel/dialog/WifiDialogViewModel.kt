package com.app.al.wifi.viewmodel.dialog

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.widget.AdapterView
import com.app.al.wifi.R
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
   * 電波強度選択
   *
   * @param parent parent
   * @param view view
   * @param position 位置
   * @param id ID
   */
  fun onSpinnerItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
    SharedPreferenceUtils.saveInt(context, ssid, position)
  }

  /**
   * 接続ボタン押下
   */
  fun onDoButtonClicked() {
    // Wifi接続
    wifiModel.connect(ssid, capabilities, password.get())
  }
}