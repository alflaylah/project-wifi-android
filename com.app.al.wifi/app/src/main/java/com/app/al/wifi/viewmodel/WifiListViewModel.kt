package com.app.al.wifi.viewmodel

import android.content.ContentValues.TAG
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.net.wifi.ScanResult
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import java.io.Serializable

/**
 * Wifi一覧ViewModel
 *
 * @param scanResult アクセスポイント検索結果
 */
class WifiListViewModel(scanResult: ScanResult) : Serializable {

  val ssId: String = scanResult.SSID
  val capabilities: String = scanResult.capabilities
  val level: String = scanResult.level.toString()
  var isLoading = ObservableBoolean()

  fun onRefresh() {
    Log.d(TAG, "onRefresh")
  }
}