package com.app.al.wifi.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst.BUNDLE_OBJECT
import com.app.al.wifi.databinding.FragmentWifiDialogBinding
import com.app.al.wifi.view.fragment.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.WifiListViewModel


/**
 * 権限ダイアログフラグメントクラス
 */
class WifiDialogFragment : BaseDialogFragment() {

  /**
   * ダイアログ生成
   *
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FragmentWifiDialogBinding>(inflater,
        R.layout.fragment_wifi_dialog, container, false)
    val wifiListViewModel = arguments.getSerializable(BUNDLE_OBJECT) as WifiListViewModel
    binding.viewModel = wifiListViewModel
    return binding.root
  }

  companion object {
    /**
     * インスタンス生成
     *
     * @param wifiListViewModel 表示メッセー
     * @return 権限ダイアログフラグメント
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