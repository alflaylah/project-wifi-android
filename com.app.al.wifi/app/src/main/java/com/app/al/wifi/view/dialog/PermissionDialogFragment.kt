package com.app.al.wifi.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.app.al.wifi.R
import com.app.al.wifi.view.dialog.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.dialog.PermissionDialogViewModel

/**
 * 権限ダイアログ
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
    val message = arguments?.getString(ARG_MESSAGE_KEY)
    val dialogBuilder = AlertDialog.Builder(context!!)
        .setMessage(message)
        .setPositiveButton(activity?.getString(R.string.permission_move), { dialog, which ->
          dismiss()
          permissionDialogViewModel.onPositiveButtonClicked()
        })
        .setNegativeButton(activity?.getString(R.string.permission_not_move), { dialog, which ->
          permissionDialogViewModel.onNegativeButtonClicked()
        })
    return dialogBuilder.create()
  }

  companion object {
    private val ARG_MESSAGE_KEY = "message_key"

    /**
     * インスタンス生成
     *
     * @param message 表示メッセージ
     * @return 権限ダイアログ
     */
    fun newInstance(message: String): PermissionDialogFragment {
      val fragment = PermissionDialogFragment()
      val args = Bundle()
      args.putString(ARG_MESSAGE_KEY, message)
      fragment.arguments = args
      return fragment
    }
  }
}