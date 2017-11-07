package com.app.al.wifi.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.app.al.wifi.R
import com.app.al.wifi.util.ApplicationUtils
import com.app.al.wifi.view.fragment.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.PermissionDialogViewModel

/**
 * 権限ダイアログFragment
 */
class PermissionDialogFragment : BaseDialogFragment() {

  private lateinit var permissionDialogViewModel: PermissionDialogViewModel

  /**
   * ダイアログ生成
   *
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    permissionDialogViewModel = PermissionDialogViewModel()
    val message = arguments?.getString(ARG_PERMISSION_NAME)
    val dialogBuilder = AlertDialog.Builder(context!!)
        .setMessage(message)
        .setPositiveButton(activity?.getString(R.string.permission_move)) { dialog, which ->
          dismiss()
          ApplicationUtils.startApplicationDetailSettings(activity!!)
        }
        .setNegativeButton(activity?.getString(R.string.permission_not_move), { dialog, which ->
          permissionDialogViewModel.onCancelClicked()
        })
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
    fun newInstance(message: String): PermissionDialogFragment {
      val fragment = PermissionDialogFragment()
      val args = Bundle()
      args.putString(ARG_PERMISSION_NAME, message)
      fragment.arguments = args
      return fragment
    }
  }
}