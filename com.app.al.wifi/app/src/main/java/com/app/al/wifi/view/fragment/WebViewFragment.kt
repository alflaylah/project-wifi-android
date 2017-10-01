package com.app.al.wifi.view.fragment

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.app.al.wifi.R
import com.app.al.wifi.view.fragment.base.BaseFragment

/**
 * WebViewFragment
 */
class WebViewFragment : BaseFragment() {

  private lateinit var webView: WebView
  private lateinit var progressBar: ProgressBar

  /**
   * onCreateView
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState savedInstanceState
   * @return View
   */
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_web_view, container, false)
    progressBar = view.findViewById(R.id.web_view)
    webView = view.findViewById(R.id.web_view)
    webView.webViewClient = LicenseWebViewClient()
    webView.loadUrl(getString(R.string.url_license))
    return view
  }

  /**
   * onDetach
   */
  override fun onDestroy() {
    super.onDestroy()
    webView.destroy()
  }

  /**
   * onDetach
   */
  override fun onDetach() {
    super.onDetach()
    dismissProgress()
  }

  private fun dismissProgress() {
  }

  /**
   * WebViewClient
   */
  inner class LicenseWebViewClient : WebViewClient() {
    /**
     * URL読込み中
     *
     * @param view    WebView
     * @param request リクエスト
     * @return boolean
     */
    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
      progressBar.visibility = View.VISIBLE
      return super.shouldOverrideUrlLoading(view, request)
    }

    /**
     * URL読込み中（非推奨）
     *
     * @param view WebView
     * @param url  url
     * @return boolean
     * @deprecated 新メソッドに置換 {@link #shouldOverrideUrlLoading(WebView, WebResourceRequest)}
     */
    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
      progressBar.visibility = View.VISIBLE
      return super.shouldOverrideUrlLoading(view, url)
    }

    /**
     * URL読込み開始
     *
     * @param view WebView
     * @param url  url
     * @param favicon favicon
     */
    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
      progressBar.visibility = View.GONE
      super.onPageStarted(view, url, favicon)
    }

    /**
     * URL読込み終了
     *
     * @param view WebView
     * @param url  url
     */
    override fun onPageFinished(view: WebView, url: String) {
      progressBar.visibility = View.GONE
      super.onPageFinished(view, url)
    }

    /**
     * URL読込み失敗
     *
     * @param view WebView
     * @param request WebResourceRequest
     * @param error WebResourceError
     */
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
      progressBar.visibility = View.GONE
    }
  }
}
