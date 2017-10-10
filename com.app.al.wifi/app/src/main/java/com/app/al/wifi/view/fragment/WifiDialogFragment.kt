package com.app.al.wifi.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog.Builder
import android.view.LayoutInflater
import android.view.WindowManager
import com.app.al.wifi.R
import com.app.al.wifi.view.fragment.base.BaseDialogFragment

/**
 * 権限ダイアログフラグメントクラス
 */
class WifiDialogFragment : BaseDialogFragment() {

  /**
   * ダイアログ生成
   *
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val message = arguments.getString(ARG_PERMISSION_NAME)

    val view = LayoutInflater.from(activity).inflate(R.layout.fragment_wifi_dialog, null)
    val dialogBuilder = Builder(activity).setView(view)
    val dialog: Dialog = dialogBuilder.create()
    dialog.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
    return dialog
  }

  companion object {
    private val ARG_PERMISSION_NAME = "permissionName"

    /**
     * インスタンス生成
     *
     * @param message 表示メッセージ
     * @return 権限ダイアログフラグメント
     */
    fun newInstance(message: String): WifiDialogFragment {
      val fragment = WifiDialogFragment()
      val args = Bundle()
      args.putString(ARG_PERMISSION_NAME, message)
      fragment.arguments = args
      return fragment
    }
  }
}