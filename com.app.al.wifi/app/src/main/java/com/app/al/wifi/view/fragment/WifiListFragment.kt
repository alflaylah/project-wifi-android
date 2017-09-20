package com.app.al.wifi.view.fragment

import android.content.Context
import android.net.wifi.ScanResult
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.common.Const
import com.app.al.wifi.common.util.PermissionUtils
import com.app.al.wifi.common.util.WifiUtils
import com.app.al.wifi.ui.ada.WifiListAdapter
import com.app.al.wifi.view.fragment.base.BaseFragment
import com.app.al.wifi.viewmodel.WifiListViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/*
 * WIFI情報一覧Fragment
 */
class WifiListFragment : BaseFragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: WifiListAdapter
  private var wifiInformationList = listOf<ScanResult>()
  private var subscribe: Disposable? = null

  @Inject
  lateinit var wifiListViewModel: WifiListViewModel

  companion object {
    /**
     * インスタンス生成
     *
     * @return WIFI情報一覧Fragment
     */
    fun newInstance(): WifiListFragment = WifiListFragment()
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
   * onDestroy
   */
  override fun onDestroy() {
    super.onDestroy()
    subscribe?.dispose()
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
    recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
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
    init()
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
        // 許可されました
        setAdapter()
        setAdapterEvent()
      } else {
        // 許可されませんでした
        PermissionUtils.checkNeverRequestPermission(this, permissions, R.string.permission_denied_message)
      }
      else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
  }

  /**
   * 初期処理
   */
  private fun init() {
    getApplicationComponent().inject(this)
  }

  /**
   * 権限初期処理
   */
  private fun initPermission() {
    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || PermissionUtils.isRequestPermission(this, Const.PERMISSIONS)) {
      setAdapter()
      setAdapterEvent()
    }
  }

  /**
   * WIFI情報一覧アダプタ設定
   */
  private fun setAdapter() {
    wifiInformationList = WifiUtils.getWifiInformationList(activity)
    adapter = WifiListAdapter(activity, wifiInformationList)
    recyclerView.adapter = adapter
  }

  /**
   * WIFI情報一覧イベント設定
   */
  private fun setAdapterEvent() {
    subscribe = adapter.clickEvent
        .compose(bindToLifecycle())
        .subscribe({ wifiListViewModel.OnItemClick(it) })
  }
}
