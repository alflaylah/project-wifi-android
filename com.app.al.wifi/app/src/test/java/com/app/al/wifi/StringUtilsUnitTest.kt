package com.app.laylah.alf.wifi

import com.app.al.wifi.util.StringUtils
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * 文字列ユーティリティテスト
 */
class StringUtilsUnitTest {

  /**
   * ダブルクォート付与テスト
   */
  @Test
  @Throws(Exception::class)
  fun double_quote_isCorrect() {
    val INPUT = "TEST"
    val OUTPUT = "\"TEST\""
    assertEquals(StringUtils.getFormatDoubleQuote(INPUT), OUTPUT)
  }
}