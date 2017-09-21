package com.app.al.wifi.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog.Builder
import com.app.al.wifi.R.string
import com.app.al.wifi.util.PermissionUtils
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
    val dialogBuilder = Builder(activity)
        .setMessage(message)
        .setPositiveButton(activity.getString(string.permission_move)) { dialog, which ->
          dismiss()
          PermissionUtils.startApplicationDetailSettings(activity)
        }
        .setNegativeButton(activity.getString(string.permission_not_move), { dialog, which -> dismiss() })
    return dialogBuilder.create()
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