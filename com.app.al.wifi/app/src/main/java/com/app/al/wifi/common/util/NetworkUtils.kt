package com.app.al.wifi.common.util

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.support.v4.app.FragmentActivity

/**
 * ネットワークユーティリティ
 */
object NetworkUtils {

  /**
   * ネットワーク通信可否
   *
   * @param context context
   * @return true：通信可能 false：通信負荷
   */
  fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
  }

  /**
   * WIFI検索結果一覧返却
   *
   * @param activity Activity
   * @return WIFI検索結果一覧
   */
  fun getWifiInformationList(activity: FragmentActivity): List<ScanResult> {
    val wifiManager = activity.getSystemService(WIFI_SERVICE) as WifiManager
    var scanResults = listOf<ScanResult>()
    if (wifiManager.startScan()) {
      // SSIDが空でない情報のみ抽出
      scanResults = wifiManager.scanResults.filter { it.SSID.isNotEmpty() }
    }
    // SSID、BSSID順にソートした状態で返却
    return scanResults.sortedWith(compareBy({ it.SSID }, { it.BSSID }))
  }
}
