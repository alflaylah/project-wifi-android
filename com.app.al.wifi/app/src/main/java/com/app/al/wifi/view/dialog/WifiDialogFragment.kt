package com.app.al.wifi.view.dialog

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst.BUNDLE_OBJECT
import com.app.al.wifi.const.DisplayConst
import com.app.al.wifi.databinding.FragmentDialogWifiBinding
import com.app.al.wifi.view.dialog.base.BaseDialogFragment
import com.app.al.wifi.viewmodel.dialog.WifiDialogViewModel
import com.app.al.wifi.viewmodel.fragment.WifiListViewModel
import org.honorato.multistatetogglebutton.MultiStateToggleButton


/**
 * WifiダイアログFragment
 */
class WifiDialogFragment : BaseDialogFragment() {

  private lateinit var wifiDialogViewModel: WifiDialogViewModel

  /**
   * ダイアログ生成
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState 引き継ぎパラメータ
   */
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding = DataBindingUtil.inflate<FragmentDialogWifiBinding>(inflater, R.layout.fragment_dialog_wifi, container, false)
    val wifiListViewModel = arguments?.getSerializable(BUNDLE_OBJECT) as WifiListViewModel
    wifiDialogViewModel = WifiDialogViewModel(context, wifiListViewModel.ssid, wifiListViewModel.capabilities)
    binding.viewModel = wifiDialogViewModel
    initToggleButton(binding)
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
   * onDestroy
   */
  override fun onDestroy() {
    wifiDialogViewModel.dispose()
    super.onDestroy()
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

  /**
   * トグルボタン初期処理
   *
   * @param binding FragmentDialogWifiBinding
   */
  private fun initToggleButton(binding: FragmentDialogWifiBinding) {
    val toggleButton = binding.root.findViewById(R.id.toggle_level) as MultiStateToggleButton
    // トグルに設定するボタン
    val buttons = arrayOfNulls<ImageButton>(5)
    val selected = BooleanArray(5)
    for (i in 0..4) {
      val imageButton = ImageButton(context)
      val resourceId = resources.getIdentifier("ic_signal_wifi_level_" + i, "mipmap", context?.packageName)
      imageButton.setImageResource(resourceId)
      imageButton.setPadding(12, 0, 12, 0)
      buttons[i] = imageButton
      // TODO
      if (i == 2) {
        selected[i] = true
      }
    }
    toggleButton.setButtons(buttons, selected)
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