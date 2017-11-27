package com.app.al.wifi.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.app.al.wifi.view.fragment.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.ConfirmDialogViewModel

/**
 * 確認ダイアログ
 */
class ConfirmDialogFragment : BaseDialogFragment() {

  private lateinit var confirmDialogViewModel: ConfirmDialogViewModel

  /**
   * ダイアログ生成
   *
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    confirmDialogViewModel = ConfirmDialogViewModel()
    val message = arguments?.getString(ConfirmDialogFragment.ARG_MESSAGE_KEY)
    val positive = arguments?.getString(ConfirmDialogFragment.ARG_POSITIVE_KEY)
    val negative = arguments?.getString(ConfirmDialogFragment.ARG_NEGATIVE_KEY)
    val dialogBuilder = AlertDialog.Builder(context!!)
        .setMessage(message)
        .setPositiveButton(positive, { dialog, which ->
          dismiss()
          confirmDialogViewModel.onPositiveButtonClicked()
        })
        .setNegativeButton(negative, { dialog, which ->
          confirmDialogViewModel.onNegativeButtonClicked()
        })
    return dialogBuilder.create()
  }

  companion object {
    private val ARG_MESSAGE_KEY = "message_key"
    private val ARG_POSITIVE_KEY = "positive_key"
    private val ARG_NEGATIVE_KEY = "negative_key"

    /**
     * インスタンス生成
     *
     * @param message 表示メッセージ
     * @param positive 表示メッセージ
     * @param negative 表示メッセージ
     * @return アプリケーション終了ダイアログ
     */
    fun newInstance(message: String, positive: String, negative: String): ConfirmDialogFragment {
      val fragment = ConfirmDialogFragment()
      val args = Bundle()
      args.putString(ARG_MESSAGE_KEY, message)
      args.putString(ARG_POSITIVE_KEY, positive)
      args.putString(ARG_NEGATIVE_KEY, negative)
      fragment.arguments = args
      return fragment
    }
  }
}