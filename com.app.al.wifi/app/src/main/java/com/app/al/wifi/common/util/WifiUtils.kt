package com.app.al.wifi.common.util

import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import com.app.al.wifi.common.Constant.SEQURITY_TYPE

/**
 * WIFIユーティリティ
 */
object WifiUtils {

  /**
   *
   */
  fun convertScanResultToWifiConfiguration(scanResult: ScanResult, password: String): WifiConfiguration? {
    val config = WifiConfiguration()

    config.SSID = '"' + scanResult.SSID + '"'
    config.hiddenSSID = false
    val securityType = getSecurity(scanResult)
    when (securityType) {
      SEQURITY_TYPE.SECURITY_NONE -> config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
      SEQURITY_TYPE.SECURITY_WEP -> {
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)
        if (password != null) {
          val length = password.length
          // WEP-40, WEP-104, and 256-bit WEP (WEP-232?)
          if ((length == 10 || length == 26 || length == 58) && password.matches("[0-9A-Fa-f]*".toRegex())) {
            config.wepKeys[0] = password
          } else {
            config.wepKeys[0] = '"' + password + '"'
          }
        }
      }
      SEQURITY_TYPE.SECURITY_PSK -> {
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        if (password != null) {
          if (password.matches("[0-9A-Fa-f]{64}".toRegex())) {
            config.preSharedKey = password
          } else {
            config.preSharedKey = '"' + password + '"'
          }
        }
      }
      else -> return null
    }
    return config
  }

  /**
   * 暗号化情報
   */
  private fun getSecurity(result: ScanResult): SEQURITY_TYPE {
    if (result.capabilities.contains("WEP")) {
      return SEQURITY_TYPE.SECURITY_WEP
    } else if (result.capabilities.contains("PSK")) {
      return SEQURITY_TYPE.SECURITY_PSK
    } else if (result.capabilities.contains("EAP")) {
      return SEQURITY_TYPE.SECURITY_EAP
    }
    return SEQURITY_TYPE.SECURITY_NONE
  }
}