@file:JvmName("StringUtils")

package com.app.al.wifi.util

import android.content.Context
import android.content.res.Resources
import java.util.Arrays

/**
 * リソースユーティリティ
 */
object ResourceUtils {

  /**
   * StringArray→List変換
   *
   * @param context applicationContext
   * @param resId リソースID
   * @return List<String>
   */
  @Throws(Resources.NotFoundException::class)
  fun getStringArrayResource(
    context: Context?,
    resId: Int
  ): List<String> {
    return Arrays.asList(*context?.resources?.getStringArray(resId))
  }
}