package com.app.al.wifi.util

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.app.al.wifi.R

/**
 * 権限要求ユーティリティ
 */
object PermissionUtils {

  val TAG = PermissionUtils.javaClass.simpleName!!
  val REQUEST_PERMISSION = 1

  // 初回要求フラグ
  var isFirstShouldShowRequest = false

  /**
   * 権限要求判定
   *
   * @param activity 遷移元Activity
   * @param permissions 要求権限
   * @return true：許可済み false：不許可
   */
  fun isRequestPermission(activity: FragmentActivity, permissions: Array<String>): Boolean {
    return isRequestPermission(activity, permissions, REQUEST_PERMISSION)
  }

  /**
   * 権限要求判定
   *
   * @param activity 遷移元Activity
   * @param permissions 要求権限
   * @param requestCode リクエストコード
   * @return true：許可済み false：不許可
   */
  fun isRequestPermission(activity: FragmentActivity, permissions: Array<String>, requestCode: Int): Boolean {
    // アプリケーションで要求した権限をユーザーが許可しているか判定
    for (permission in permissions) {
      if (ContextCompat.checkSelfPermission(activity,
          permission) != PackageManager.PERMISSION_GRANTED) {
        // ユーザー許可ダイアログを表示
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
        return false
      }
    }
    // ユーザーが許可している(6.0未満は自動で許可)
    return true
  }

  /**
   * 権限要求結果判定
   *
   * @param grantResults 要求結果
   * @return true：許可 false：不許可
   */
  fun isRequestPermissionResult(grantResults: IntArray): Boolean {
    var isGranted = true
    for (grantResult in grantResults) {
      isGranted = isGranted && (PackageManager.PERMISSION_GRANTED == grantResult)
    }
    return isGranted
  }

  /**
   * 今後表示しないチェック
   *
   * @param activity 遷移元Activity
   * @param permissions 要求権限
   * @param messageId メッセージID
   */
  fun checkNeverRequestPermission(activity: FragmentActivity, permissions: Array<String>, messageId: Int) {
    checkNeverRequestPermission(activity, permissions, activity.getString(messageId))
  }

  /**
   * 今後表示しないチェック
   *
   * @param activity 遷移元Activity
   * @param permissions 要求権限
   * @param message メッセージ
   */
  fun checkNeverRequestPermission(activity: FragmentActivity, permissions: Array<String>, message: String) {
    val isShouldShowRequest = permissions.any { permission ->
      !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }
    // 今後表示しないが選択されていなければ処理終了
    if (!isShouldShowRequest) return
    // 今後表示しないを選択したことがない
    if (!isFirstShouldShowRequest) {
      // 今後表示しないを初めて選択した場合、処理終了
      isFirstShouldShowRequest = true
      return
    }
    // 今後表示しないを選択済み、かつ、選択が2回目以降ならダイアログを表示する
    if (isShouldShowRequest && isFirstShouldShowRequest) Handler().post {
      showDialog(activity.supportFragmentManager, message)
    }
  }

  /**
   * アプリケーション詳細設定画面遷移
   *
   * @param activity 遷移元Activity
   */
  private fun startApplicationDetailSettings(activity: FragmentActivity) {
    val parse = String.format(activity.getString(R.string.permission_package), activity.packageName)
    activity.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(parse)))
  }

  /**
   * ダイアログ表示
   *
   * @param fragmentManager fragmentManager
   * @param message メッセージ
   */
  private fun showDialog(fragmentManager: FragmentManager, message: String) {
    PermissionDialogFragment.newInstance(message).show(fragmentManager, TAG)
  }

  /**
   * 権限ダイアログフラグメントクラス
   */
  class PermissionDialogFragment : DialogFragment() {
    /**
     * ダイアログ生成
     *
     * @param savedInstanceState 引き継ぎパラメータ
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      val message = arguments.getString(ARG_PERMISSION_NAME)
      val dialogBuilder = AlertDialog.Builder(activity)
          .setMessage(message)
          .setPositiveButton(activity.getString(R.string.permission_move), { dialog, which ->
            dismiss()
            startApplicationDetailSettings(activity)
          })
          .setNegativeButton(activity.getString(R.string.permission_move), { dialog, which -> dismiss() })
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
}
