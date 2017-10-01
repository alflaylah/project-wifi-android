package com.app.al.wifi.viewmodel

import android.app.Activity
import android.content.Context
import android.view.View

/**
 * WebViewModel
 *
 * @param context コンテキスト
 */
class WebViewViewModel(context: Context) {

  private var context: Context = context

  /**
   * 戻る押下時イベント
   */
  fun onBackClicked(view: View) {
    (context as Activity).onBackPressed()
  }
}
