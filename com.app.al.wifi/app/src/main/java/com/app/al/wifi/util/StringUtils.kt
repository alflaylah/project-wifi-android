package com.app.al.wifi.util

import android.text.TextUtils
import com.app.al.wifi.const.ApplicationConst

/**
 * 文字列ユーティリティ
 */
object StringUtils {

  /**
   * ダブルクォート付与
   *
   * @param value 文字列
   * @return 文字列(ダブルクォート付与)
   */
  fun getFormatDoubleQuote(value: String): String = ApplicationConst.DOUBLE_QUOTE + value + ApplicationConst.DOUBLE_QUOTE

  /**
   * null or 空判定
   *
   * @param value 文字列
   * @return true：null or 空 false：null or 空ではない
   */
  fun isEmpty(value: String?): Boolean = ((value == null) || TextUtils.isEmpty(value.trim()))
}