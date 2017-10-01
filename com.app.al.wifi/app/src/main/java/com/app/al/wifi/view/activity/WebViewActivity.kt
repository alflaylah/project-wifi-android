package com.app.al.wifi.view.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.app.al.wifi.R
import com.app.al.wifi.R.layout
import com.app.al.wifi.view.activity.base.BaseActivity
import com.app.al.wifi.viewmodel.WebViewViewModel

/**
 * WebViewアクティビティ
 */
class WebViewActivity : BaseActivity() {

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_web_view)
    init()
  }

  /**
   * 初期処理
   */
  private fun init() {
    val binding: ViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
    val viewModel = WebViewViewModel(this)
  }
}
