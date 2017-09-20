package com.app.al.wifi.common.util

import android.content.Context
import android.net.ConnectivityManager


/**
 * ネットワークユーティリティ
 */
object NetworkUtils {

  /**
   * ネットワーク通信状態を返却する
   *
   * @param context context
   * @return true：通信可能 false：通信不可
   */
  fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
  }

  /**
   * WIFI接続を有効にする
   *
   * @param context context
   */
  fun enable(context: Context) {
    WifiUtils.enable(context)
  }

  /**
   * WIFI接続の状態を返却する
   *
   * @param context context
   * @return true：WIFI接続中 false：WIFI未接続
   */
  fun isWifiConnected(context: Context): Boolean = WifiUtils.isConnected(context)
}
