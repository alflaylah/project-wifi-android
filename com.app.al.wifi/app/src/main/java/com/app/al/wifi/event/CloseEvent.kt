package com.app.al.wifi.event

/**
 * クローズイベント
 *
 * @param closeType クローズ対象判定ID
 */
class CloseEvent(val closeType: CloseType) {

  companion object {
    // クローズ対象
    enum class CloseType(val type: String) {
      APPLICATION("NONE"),
      DIALOG("WEP")
    }
  }
}