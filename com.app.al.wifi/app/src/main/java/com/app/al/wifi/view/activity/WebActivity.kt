package com.app.al.wifi.view.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.const.ApplicationConst.NavigationIconEventType.RETURN
import com.app.al.wifi.view.activity.base.BaseActivity

/**
 * Web画面Activity
 */
class WebActivity : BaseActivity() {

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web)
    init()
  }

  // class methods

  /**
   * 初期処理
   */
  private fun init() {
    initToolBar(R.string.title_license, RETURN)
    initWebView()
  }

  /**
   * WebView初期処理
   */
  private fun initWebView() {
    val webView = findViewById<WebView>(R.id.web)
    webView.webViewClient = WebViewClient()
    webView.loadUrl(intent?.getStringExtra(ApplicationConst.BUNDLE_URL))
  }
}
