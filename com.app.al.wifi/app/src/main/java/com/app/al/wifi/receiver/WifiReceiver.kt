package com.app.al.wifi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.event.WifiEvent
import com.app.al.wifi.event.bus.RxBusProvider

/**
 * Wifiレシーバー
 */
class WifiReceiver : BroadcastReceiver() {

  private val TAG = WifiReceiver::class.simpleName!!

  private val IGNORE_01 = "0x"
  private val IGNORE_02 = "<unknown ssid>"

  /**
   * onReceive
   *
   * @param context コンテキスト
   * @param intent インテント
   */
  override fun onReceive(context: Context, intent: Intent) {
    if (intent.action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
      val networkInfo = intent.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)
      val ssid: String? = networkInfo.extraInfo
      if (ssid.isNullOrBlank() || ssid.equals(IGNORE_01) || ssid.equals(IGNORE_02)) {
        // TODO
        // ssidを取得できていないようなら終了
        return
      }
      when (networkInfo.state!!) {
        NetworkInfo.State.DISCONNECTED -> {
          Log.d(TAG, NetworkInfo.State.DISCONNECTED.toString())
        }
        NetworkInfo.State.SUSPENDED -> {
          Log.d(TAG, NetworkInfo.State.SUSPENDED.toString())
        }
        NetworkInfo.State.CONNECTING -> {
          val message = String.format(context.getString(R.string.wifi_connecting_message), ssid?.replace(ApplicationConst.DOUBLE_QUOTE, ApplicationConst.EMPTY))
          RxBusProvider.instance.post(WifiEvent(message))
          Log.d(TAG, NetworkInfo.State.CONNECTING.toString())
        }
        NetworkInfo.State.CONNECTED -> {
          val message = String.format(context.getString(R.string.wifi_connected_message), ssid?.replace(ApplicationConst.DOUBLE_QUOTE, ApplicationConst.EMPTY))
          RxBusProvider.instance.post(WifiEvent(message))
          Log.d(TAG, NetworkInfo.State.CONNECTED.toString())
        }
        NetworkInfo.State.DISCONNECTING -> {
          Log.d(TAG, NetworkInfo.State.DISCONNECTING.toString())
        }
        NetworkInfo.State.UNKNOWN -> {
          Log.d(TAG, NetworkInfo.State.UNKNOWN.toString())
        }
      }
    }
  }
}