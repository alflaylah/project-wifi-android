package com.app.al.wifi.util

import android.content.Context
import android.content.SharedPreferences
import com.app.al.wifi.const.ApplicationConst

/**
 * SharedPreferencesユーティリティ
 */
object SharedPreferenceUtils {

  private lateinit var sharedPreferences: SharedPreferences

  /**
   * 保存
   *
   * @param context Context
   * @param key キー
   * @param value 値
   * @return 処理結果
   */
  fun save(context: Context, key: String, value: String): Boolean {
    sharedPreferences = context.getSharedPreferences(ApplicationConst.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    return editor.commit()
  }

  /**
   * 読込
   *
   * @param context Context
   * @param key キー
   * @return 読込結果
   */
  fun readString(context: Context, key: String): String {
    sharedPreferences = context.getSharedPreferences(ApplicationConst.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, ApplicationConst.EMPTY)
  }

  /**
   * 削除
   *
   * @param context Context
   * @param key キー
   * @return 処理結果
   */
  fun remove(context: Context, key: String): Boolean {
    val editor = sharedPreferences.edit()
    editor.remove(key)
    return editor.commit()
  }
}
