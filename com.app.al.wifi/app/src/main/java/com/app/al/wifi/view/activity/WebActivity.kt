package com.app.al.wifi.view.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
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

  /**
   * 初期処理
   */
  override fun init() {
    super.init()
    setTitle()
    initWebView()
  }

  /**
   * タイトル設定
   */
  private fun setTitle() {
    val toolbar: Toolbar? = findViewById(R.id.toolbar)
    // URLからタイトル名を設定
    if (getString(R.string.url_license).contains(intent?.getStringExtra(ApplicationConst.BUNDLE_URL).toString())) {
      toolbar?.setTitle(R.string.title_license)
    }
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
