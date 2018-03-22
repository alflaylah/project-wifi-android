package com.app.al.wifi

import com.app.al.wifi.extensions.getFormatDoubleQuote
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
  fun doubleQuoteIsCorrect() {
    val input = "TEST"
    val output = "\"TEST\""
    assertEquals(input.getFormatDoubleQuote(), output)
  }
}