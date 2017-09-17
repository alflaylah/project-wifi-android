package com.app.al.wifi.common.util

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import com.app.al.wifi.common.Const
import com.app.al.wifi.common.Const.SECURITY_TYPE


/**
 * WIFIユーティリティ
 */
object WifiUtils {

  /**
   * 接続
   *
   * @param context context
   * @param ssid SSID
   * @param capabilities セキュリティ情報
   * @param password パスワード
   */
  fun connect(context: Context, ssid: String, capabilities: String, password: String) {
    val wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager
    val networkId = wifiManager.addNetwork(getWifiConfiguration(ssid, capabilities, password))
    if (networkId != -1) {
      wifiManager.enableNetwork(networkId, true)
    } else {
    }
  }

  /**
   * 接続設定返却
   *
   * @param ssid SSID
   * @param capabilities セキュリティ情報
   * @param password パスワード
   * @return WIFI接続設定
   */
  private fun getWifiConfiguration(ssid: String, capabilities: String, password: String): WifiConfiguration? {
    val wifiConfiguration = WifiConfiguration()
    val securityType = getSecurity(capabilities)
    when (securityType) {
      SECURITY_TYPE.SECURITY_NONE -> wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
      SECURITY_TYPE.SECURITY_WEP -> {
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)
        wifiConfiguration.wepKeys[0] = getPassword(securityType, password)
      }
      SECURITY_TYPE.SECURITY_PSK -> {
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        wifiConfiguration.preSharedKey = getPassword(securityType, password)
      }
    }
    wifiConfiguration.SSID = Const.DOUBLE_QUOTE + ssid + Const.DOUBLE_QUOTE
    wifiConfiguration.hiddenSSID = false
    return wifiConfiguration
  }

  /**
   * セキュリティ情報返却
   *
   * @return セキュリティ情報
   */
  private fun getSecurity(capabilities: String): SECURITY_TYPE = when {
    capabilities.contains(SECURITY_TYPE.SECURITY_WEP.securityType) -> SECURITY_TYPE.SECURITY_WEP
    capabilities.contains(SECURITY_TYPE.SECURITY_PSK.securityType) -> SECURITY_TYPE.SECURITY_PSK
    else -> SECURITY_TYPE.SECURITY_NONE
  }

  /**
   * パスワード返却
   *
   * @param securityType セキュリティ情報
   * @param password パスワード
   * @return セキュリティ情報
   */
  private fun getPassword(securityType: SECURITY_TYPE, password: String): String = when (securityType) {
    SECURITY_TYPE.SECURITY_WEP -> {
      if ((password.length == 10 || password.length == 26) && password.matches(Const.REGEX_WEP.toRegex())) {
        password
      } else {
        StringUtils.getFormatDoubleQuote(password)
      }
    }
    SECURITY_TYPE.SECURITY_PSK -> {
      if (password.matches(Const.REGEX_PSK.toRegex())) {
        password
      } else {
        StringUtils.getFormatDoubleQuote(password + Const.DOUBLE_QUOTE)
      }
    }
    else -> {
      Const.EMPTY
    }
  }
}