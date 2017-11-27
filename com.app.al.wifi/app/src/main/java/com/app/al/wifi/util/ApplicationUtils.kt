package com.app.al.wifi.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.FragmentActivity
import com.app.al.wifi.R

/**
 * アプリケーションユーティリティ
 */
object ApplicationUtils {

  /**
   * 画面遷移
   *
   * @param context コンテキスト
   * @param clazz 遷移先Activity
   */
  fun startActivity(context: Context, clazz: Class<out Activity>) {
    startActivity(context, clazz, null)
  }

  /**
   * 画面遷移
   *
   * @param context コンテキスト
   * @param clazz 遷移先Activity
   * @param bundle 引継ぎパラメータ
   */
  fun startActivity(context: Context, clazz: Class<out Activity>, bundle: Bundle? = null) {
    val intent = Intent(context, clazz)
    if (bundle != null) {
      intent.putExtras(bundle)
    }
    context.startActivity(intent)
  }

  /**
   * アプリケーション設定画面遷移
   *
   * @param activity 遷移元Activity
   */
  fun startApplicationDetailSettings(activity: FragmentActivity) {
    val parse = String.format(activity.getString(R.string.permission_package), activity.packageName)
    activity.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(parse)))
  }
}