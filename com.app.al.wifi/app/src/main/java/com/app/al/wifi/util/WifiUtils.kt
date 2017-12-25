package com.app.al.wifi.util

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.const.ApplicationConst.WifiSecurityType
import com.app.al.wifi.const.ApplicationConst.WifiSecurityType.PSK
import com.app.al.wifi.const.ApplicationConst.WifiSecurityType.WEP

/**
 * WIFIユーティリティ
 */
object WifiUtils {

  /**
   * 接続可能にする
   *
   * @param context applicationContext
   * @return true：WIFI接続可能 false：WIFI接続不可
   */
  fun enable(context: Context) {
    val wifiManager = context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
    wifiManager.isWifiEnabled = true
  }

  /**
   * 接続の状態を返却する
   *
   * @param context applicationContext
   * @return true：接続中 false：未接続
   */
  fun isConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
  }

  /**
   * 接続
   *
   * @param context applicationContext
   * @param ssid SSID
   * @param capabilities セキュリティ情報
   * @param password パスワード
   */
  fun connect(context: Context, ssid: String, capabilities: String, password: String) {
    val wifiManager = context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
    // SSIDが端末に登録済みか判定
    val targetWifiConfiguration: WifiConfiguration? = wifiManager.configuredNetworks.lastOrNull {
      it.SSID.contains(ssid)
    }
    if (targetWifiConfiguration != null) {
      // 登録済の場合
      // 接続します
      connect(context, targetWifiConfiguration.networkId)
    } else {
      // 未登録の場合
      // 登録します
      val networkId = wifiManager.addNetwork(getWifiConfiguration(ssid, capabilities, password))
      if (networkId != -1) {
        // 登録成功
        // 接続先を切断して新たに接続
        connect(context, networkId)
      } else {
        // 登録失敗
      }
    }
  }

  /**
   * 接続
   *
   * @param context applicationContext
   * @param networkId ネットワークID
   */
  private fun connect(context: Context, networkId: Int) {
    disconnect(context)
    (context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager).enableNetwork(networkId, true)
  }

  /**
   * 切断
   *
   * @param context applicationContext
   */
  fun disconnect(context: Context) {
    val wifiManager = context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
    wifiManager.configuredNetworks.forEach {
      wifiManager.enableNetwork(it.networkId, false)
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
      WifiSecurityType.NONE -> wifiConfiguration.allowedKeyManagement.set(
          WifiConfiguration.KeyMgmt.NONE)
      WifiSecurityType.WEP -> {
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE)
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN)
        wifiConfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED)
        wifiConfiguration.wepKeys[0] = getPassword(securityType, password)
      }
      WifiSecurityType.PSK -> {
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK)
        wifiConfiguration.preSharedKey = getPassword(securityType, password)
      }
    }
    wifiConfiguration.SSID = ApplicationConst.DOUBLE_QUOTE + ssid + ApplicationConst.DOUBLE_QUOTE
    wifiConfiguration.hiddenSSID = false
    return wifiConfiguration
  }

  /**
   * セキュリティ情報返却
   *
   * @return セキュリティ情報
   */
  private fun getSecurity(capabilities: String): WifiSecurityType = when {
    capabilities.contains(WifiSecurityType.WEP.type) -> WifiSecurityType.WEP
    capabilities.contains(WifiSecurityType.PSK.type) -> WifiSecurityType.PSK
    else -> WifiSecurityType.NONE
  }

  /**
   * パスワード返却
   *
   * @param wifiSecurityType セキュリティ情報
   * @param password パスワード
   * @return セキュリティ情報
   */
  private fun getPassword(wifiSecurityType: WifiSecurityType, password: String): String = when (wifiSecurityType) {
    WEP -> {
      if ((password.length == 10 || password.length == 26) && password.matches(ApplicationConst.REGEX_WEP.toRegex())) {
        password
      } else {
        password.getFormatDoubleQuote()
      }
    }
    PSK -> {
      if (password.matches(ApplicationConst.REGEX_PSK.toRegex())) {
        password
      } else {
        password.getFormatDoubleQuote()
      }
    }
    else -> {
      ApplicationConst.EMPTY
    }
  }

  /**
   * 検索結果一覧返却
   *
   * @param context Context
   * @return 検索結果一覧
   */
  fun getWifiList(context: Context?): List<ScanResult> {
    val wifiManager = context?.applicationContext?.getSystemService(WIFI_SERVICE) as WifiManager
    var scanResults = listOf<ScanResult>()
    if (wifiManager.startScan()) {
      // TODO 条件が雑
      scanResults = wifiManager.scanResults.
          filter {
            // SSIDが空ではない
            it.SSID.isNotEmpty()
          }.
          sortedByDescending {
            // levelで降順
            it.level
          }.
          distinctBy {
            // SSIDの重複排除
            it.SSID
          }.
          sortedBy {
            // SSIDで昇順
            it.SSID
          }
    }
    return scanResults
  }

  /**
   * 接続履歴一覧返却
   *
   * @param context Context
   * @return 接続履歴一覧
   */
  private fun getHistoryList(context: Context?): List<WifiConfiguration> {
    val wifiManager = context?.applicationContext?.getSystemService(WIFI_SERVICE) as WifiManager
    return wifiManager.configuredNetworks.toList()
  }

  /**
   * 除外判定
   *
   * @param ssid SSID
   * @return true：除外します false：除外しない
   */
  fun isExclude(ssid: String?): Boolean {
    if (ssid.isNullOrBlank() || ApplicationConst.IGNORE_01.contains(ssid.toString()) || ApplicationConst.IGNORE_02.contains(ssid.toString())) {
      return true
    }
    return false
  }

  /**
   * 接続履歴判定
   *
   * @param context Context
   * @param ssid SSID
   * @return true：過去に利用している履歴あり false：過去に利用している履歴なし
   */
  fun isAccessPointHistory(context: Context?, ssid: String): Boolean {
    var wifiHistoryList = getHistoryList(context).filter { wifiConfiguration ->
      wifiConfiguration.SSID.contains(ssid)
    }
    if (!wifiHistoryList.isEmpty()) {
      return true
    }
    return false
  }

  /**
   * 利用判定
   *
   * @param context Context
   * @param ssid SSID
   * @return true：利用中 false：利用していない
   */
  fun isAccessPointConnecting(context: Context?, ssid: String): Boolean {
    val wifiManager = context?.applicationContext?.getSystemService(WIFI_SERVICE) as WifiManager
    val wifiInfo = wifiManager.connectionInfo
    if (ssid.contains(wifiInfo.ssid.replace(ApplicationConst.DOUBLE_QUOTE, ApplicationConst.EMPTY))) {
      return true
    }
    return false
  }
}