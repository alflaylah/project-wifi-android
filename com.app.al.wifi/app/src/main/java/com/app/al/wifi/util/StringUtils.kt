package com.app.al.wifi.util

import android.content.Context
import android.content.res.Resources
import com.app.al.wifi.const.ApplicationConst
import java.util.Arrays

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
   * StringArray→List変換
   *
   * @param context applicationContext
   * @param resourceId リソースID
   * @return List<String>
   */
  @Throws(Resources.NotFoundException::class)
  fun getStringArrayResource(context: Context?, resourceId: Int): List<String> {
    return Arrays.asList(*context?.resources?.getStringArray(resourceId))
  }
}