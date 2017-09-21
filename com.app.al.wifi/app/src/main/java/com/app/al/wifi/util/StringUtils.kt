package com.app.al.wifi.util

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
}