package com.app.al.wifi.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst.BUNDLE_OBJECT
import com.app.al.wifi.const.DisplayConst
import com.app.al.wifi.databinding.FragmentWifiDialogBinding
import com.app.al.wifi.view.fragment.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.WifiListViewModel


/**
 * Wifiパスワード入力ダイアログFragment
 */
class WifiDialogFragment : BaseDialogFragment() {

  /**
   * ダイアログ生成
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FragmentWifiDialogBinding>(inflater, R.layout.fragment_wifi_dialog, container, false)
    val wifiListViewModel = arguments?.getSerializable(BUNDLE_OBJECT) as WifiListViewModel
    binding.viewModel = wifiListViewModel
    return binding.root
  }

  /**
   * onActivityCreated
   *
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initLayout()
  }

  /**
   * レイアウト初期処理
   */
  private fun initLayout() {
    val layoutParams = dialog.window.attributes
    activity?.windowManager?.defaultDisplay?.getMetrics(resources.displayMetrics)
    // 画面幅×指定スケールでダイアログを表示
    layoutParams.width = (resources.displayMetrics.widthPixels * DisplayConst.DIALOG_WIDTH_SCALE).toInt()
    dialog.window.attributes = layoutParams
  }

  companion object {
    /**
     * インスタンス生成
     *
     * @param wifiListViewModel 表示
     * @return Wifiパスワード入力ダイアログFragment
     */
    fun newInstance(wifiListViewModel: WifiListViewModel): WifiDialogFragment {
      val fragment = WifiDialogFragment()
      val bundle = Bundle()
      bundle.putSerializable(BUNDLE_OBJECT, wifiListViewModel)
      fragment.arguments = bundle
      return fragment
    }
  }
}