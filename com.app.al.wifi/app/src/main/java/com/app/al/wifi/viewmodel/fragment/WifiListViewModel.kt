package com.app.al.wifi.viewmodel.fragment

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.net.wifi.ScanResult
import android.widget.ImageView
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.util.WifiUtils
import java.io.Serializable

/**
 * Wifi一覧画面ViewModel
 *
 * @param context コンテキスト
 * @param scanResult アクセスポイント検索結果
 */
class WifiListViewModel(val context: Context?, scanResult: ScanResult) : Serializable {

  val ssid: String = scanResult.SSID
  val capabilities: String = scanResult.capabilities
  private val level: Int = scanResult.level

  /**
   * Wifiの接続状態に応じたステータスの返却
   *
   * @return 接続状態
   */
  fun getStatus(): String {
    // 現在、利用中
    if (WifiUtils.isAccessPointConnecting(context, ssid)) {
      return context?.getString(R.string.wifi_connected_status).toString()
    }
    // 過去に履歴あり
    if (WifiUtils.isAccessPointHistory(context, ssid)) {
      return context?.getString(R.string.wifi_connected_history_status).toString()
    }
    return ApplicationConst.EMPTY
  }

  /**
   * Levelに応じたWifi画像の返却
   *
   * @return Wifi画像リソースID
   */
  fun getWifiSignal(): Int = when {
    (level >= -50) -> {
      R.mipmap.ic_signal_wifi_level_4
    }
    (-50 < level && level >= -60) -> {
      R.mipmap.ic_signal_wifi_level_3
    }
    (-60 < level && level >= -70) -> {
      R.mipmap.ic_signal_wifi_level_2
    }
    (-70 < level && level >= -80) -> {
      R.mipmap.ic_signal_wifi_level_1
    }
    else -> {
      R.mipmap.ic_signal_wifi_level_0
    }
  }

  companion object {
    /**
     * 画像設定
     *
     * @param imageView 対象View
     * @param imageUrl 画像URL
     */
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
      if (imageUrl.isNullOrEmpty()) {
        imageView.setImageURI(null)
      } else {
        imageView.setImageURI(Uri.parse(imageUrl))
      }
    }

    /**
     * 画像設定
     *
     * @param imageView 対象View
     * @param imageUri 画像URI
     */
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, imageUri: Uri?) {
      imageView.setImageURI(imageUri)
    }

    /**
     * 画像設定
     *
     * @param imageView 対象View
     * @param drawable 画像Drawable
     */
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, drawable: Drawable?) {
      imageView.setImageDrawable(drawable)
    }

    /**
     * 画像設定
     *
     * @param imageView 対象View
     * @param resourceId 画像リソースID
     */
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImage(imageView: ImageView, resourceId: Int) {
      imageView.setImageResource(resourceId)
    }
  }
}