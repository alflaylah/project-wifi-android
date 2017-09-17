package com.app.al.wifi.common.util

import com.app.al.wifi.common.Const

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
  fun getFormatDoubleQuote(value: String): String = Const.DOUBLE_QUOTE + value + Const.DOUBLE_QUOTE
}