@file:JvmName("StringUtils")

package com.app.al.wifi.extensions

import com.app.al.wifi.const.ApplicationConst

// 文字列拡張関数

/**
 * ダブルクォート付与
 *
 * @return 文字列(ダブルクォート付与)
 */
fun String.getFormatDoubleQuote() = ApplicationConst.DOUBLE_QUOTE + this + ApplicationConst.DOUBLE_QUOTE
