package com.app.al.wifi.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

/**
 * Wifiレシーバー
 */
class WifiReceiver : BroadcastReceiver() {

  private val TAG = WifiReceiver::class.simpleName!!

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
        NetworkInfo.State.DISCONNECTED -> {
          Toast.makeText(context, NetworkInfo.State.DISCONNECTED.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.DISCONNECTED.toString())
        }
        NetworkInfo.State.SUSPENDED -> {
          Toast.makeText(context, NetworkInfo.State.SUSPENDED.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.SUSPENDED.toString())
        }
        NetworkInfo.State.CONNECTING -> {
          Toast.makeText(context, NetworkInfo.State.CONNECTING.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.CONNECTING.toString())
        }
        NetworkInfo.State.CONNECTED -> {
          Toast.makeText(context, NetworkInfo.State.CONNECTED.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.CONNECTED.toString())
        }
        NetworkInfo.State.DISCONNECTING -> {
          Toast.makeText(context, NetworkInfo.State.DISCONNECTING.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.DISCONNECTING.toString())
        }
        NetworkInfo.State.UNKNOWN -> {
          Toast.makeText(context, NetworkInfo.State.UNKNOWN.toString(), Toast.LENGTH_LONG).show()
          Log.d(TAG, NetworkInfo.State.UNKNOWN.toString())
        }
      }
    }
  }
}