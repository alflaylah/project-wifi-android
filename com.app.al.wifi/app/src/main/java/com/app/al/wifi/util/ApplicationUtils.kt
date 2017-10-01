package com.app.al.wifi.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

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
}