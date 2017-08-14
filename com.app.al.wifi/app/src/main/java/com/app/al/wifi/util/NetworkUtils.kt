package com.app.al.wifi.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.support.v4.app.FragmentActivity

/**
 * ネットワークユーティリティ
 */
object NetworkUtils {

  fun isOnline(context: Context): Boolean {
    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connMgr.activeNetworkInfo
    // You should always check isConnected(), since isConnected()
    // handles cases like unstable network state.
    return networkInfo != null && networkInfo.isConnected
  }

  /**
   * WIFI情報返却
   *
   * @param activity Activity
   * @return WIFI情報リスト
   */
  fun getWifiInformationList(activity: FragmentActivity): WifiInfo {
    val wifiManager = activity.getSystemService(WIFI_SERVICE) as WifiManager
    wifiManager.startScan()
    val scanResults = wifiManager.connectionInfo
    return scanResults
  }
}
