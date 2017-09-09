package com.app.al.wifi.view.fragment

import android.content.Context
import android.net.wifi.ScanResult
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.common.Constant
import com.app.al.wifi.common.util.NetworkUtils
import com.app.al.wifi.common.util.PermissionUtils
import com.app.al.wifi.ui.ada.WifiListAdapter
import com.app.al.wifi.view.event.WifiListEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/*
 * Wifi一覧Fragment
 */
class WifiListFragment : Fragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: WifiListAdapter
  // WIFI情報リスト
  private var wifiInformationList = arrayListOf<ScanResult>()

  companion object {
    /**
     * インスタンス生成
     *
     * @return Wifi一覧Fragment
     */
    fun newInstance(): WifiListFragment {
      return WifiListFragment()
    }
  }

  /**
   * onAttach
   *
   * @param context context
   */
  override fun onAttach(context: Context) {
    super.onAttach(context)
  }

  /**
   * onStart
   */
  override fun onStart() {
    super.onStart()
    EventBus.getDefault().register(this)
  }

  /**
   * onStop
   */
  override fun onStop() {
    super.onStop()
    EventBus.getDefault().unregister(this)
  }

  /**
   * onCreateView
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater!!.inflate(R.layout.fragment_wifi_list, container, false)
    recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view) as RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(activity)
    return view
  }

  /**
   * onActivityCreated
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    initPermission()
  }

  /**
   * 権限要求結果
   *
   * @param requestCode requestCode
   * @param permissions permissions
   * @param grantResults grantResults
   */
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
      PermissionUtils.REQUEST_PERMISSION -> if (PermissionUtils.isRequestPermissionResult(grantResults)) {
        wifiInformationList = NetworkUtils.getWifiInformationList(activity)
        adapter = WifiListAdapter(activity, wifiInformationList)
        recyclerView!!.adapter = adapter
      } else {
        // 許可されませんでした
        PermissionUtils.checkNeverRequestPermission(activity, permissions, R.string.permission_denied_message)
      }
      else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
  }

  /**
   *
   */
  @Subscribe(threadMode = ThreadMode.MAIN)
  fun onWifiClicked(onWifiEvent: WifiListEvent) {
    onWifiEvent.getView()
    onWifiEvent.getPosition()
  }

  /**
   * 権限初期処理
   */
  private fun initPermission() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      wifiInformationList = NetworkUtils.getWifiInformationList(activity)
      adapter = WifiListAdapter(activity, wifiInformationList)
      recyclerView!!.adapter = adapter
    }
    if (PermissionUtils.isRequestPermission(activity, Constant.PERMISSIONS)) {
      wifiInformationList = NetworkUtils.getWifiInformationList(activity)
      adapter = WifiListAdapter(activity, wifiInformationList)
      recyclerView!!.adapter = adapter
    }
  }
}
