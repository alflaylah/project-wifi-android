package com.app.al.wifi.util

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
    val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
  }

  /**
   * WIFI情報返却
   *
   * @param activity Activity
   * @return WIFI情報リスト
   */
  fun getWifiInformationList(activity: FragmentActivity): ArrayList<ScanResult> {
    val wifiManager = activity.getSystemService(WIFI_SERVICE) as WifiManager
    var scanResults = mutableListOf<ScanResult>()
    if (wifiManager.startScan()) {
      scanResults = wifiManager.scanResults
    }
    return scanResults as ArrayList<ScanResult>
  }
}
