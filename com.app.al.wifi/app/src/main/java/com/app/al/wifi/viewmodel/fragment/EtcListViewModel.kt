package com.app.al.wifi.viewmodel.fragment

import com.app.al.wifi.event.StartEvent
import com.app.al.wifi.event.bus.RxBusProvider
import java.io.Serializable

/**
 * その他一覧画面ViewModel
 *
 * @param title タイトル
 */
class EtcListViewModel(var title: String) : Serializable {

  var value: String? = null

  /**
   * セカンダリコンストラクタ
   *
   * @param title タイトル
   * @param value 値
   */
  constructor(
    title: String,
    value: String
  ) : this(title) {
    this.value = value
  }

  /**
   * 項目押下
   */
  fun onItemClicked() {
    // バージョンは何もしない
    if (isVersion()) {
      return
    }
    RxBusProvider.instance.post(StartEvent(StartEvent.APP_LICENSE))
  }

  /**
   * バージョン項目の可否返却
   *
   * @return true：バージョン項目 false：バージョン項目以外
   */
  private fun isVersion(): Boolean = !value.isNullOrEmpty()
}