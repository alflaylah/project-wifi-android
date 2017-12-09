package com.app.al.wifi.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.event.WifiEvent
import com.app.al.wifi.event.bus.RxBusProvider
import com.app.al.wifi.util.WifiUtils
import javax.inject.Inject

/**
 * Wifiレシーバー
 */
class WifiReceiver : BroadcastReceiver() {

  private val TAG = WifiReceiver::class.simpleName!!

  @Inject
  lateinit var applicationContext: Context

  /**
   * onReceive
   *
   * @param context コンテキスト
   * @param intent インテント
   */
  override fun onReceive(context: Context, intent: Intent) {
    if (intent.action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
      val networkInfo = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
      when (networkInfo.state!!) {
        NetworkInfo.State.SUSPENDED, NetworkInfo.State.DISCONNECTING, NetworkInfo.State.DISCONNECTED, NetworkInfo.State.UNKNOWN -> {
          Log.d(TAG, networkInfo.state.toString())
        }
        NetworkInfo.State.CONNECTING, NetworkInfo.State.CONNECTED -> {
          connectWifi(networkInfo, context, intent)
        }
      }
    }
  }

  /**
   * Wifi接続中/完了時の処理
   *
   * @param networkInfo NetworkInfo
   * @param context コンテキスト
   * @param intent インテント
   * @return true：除外します false：除外しません
   */
  private fun connectWifi(networkInfo: NetworkInfo, context: Context, intent: Intent) {
    if (WifiUtils.isExclude(networkInfo.extraInfo)) {
      // SSIDが除外対象ならここで終了
      return
    }
    val wifiInfo = intent.getParcelableExtra<WifiInfo>(WifiManager.EXTRA_WIFI_INFO)
//    if (wifiInfo != null && wifiInfo.rssi < 0) {
//      // 電波強度、切断
//      WifiUtils.disconnect(context)
//      return
//    }
    val message: String = if (networkInfo.state == NetworkInfo.State.CONNECTING) {
      String.format(context.getString(R.string.wifi_connecting_message), networkInfo.extraInfo.replace(ApplicationConst.DOUBLE_QUOTE, ApplicationConst.EMPTY))
    } else {
      String.format(context.getString(R.string.wifi_connected_message), networkInfo.extraInfo.replace(ApplicationConst.DOUBLE_QUOTE, ApplicationConst.EMPTY))
    }
    RxBusProvider.instance.post(WifiEvent(message))
    Log.d(TAG, networkInfo.state.toString())
  }
}