package com.app.al.wifi.view.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.event.WifiConnectEvent
import com.app.al.wifi.ui.ada.WifiListAdapter
import com.app.al.wifi.util.PermissionUtils
import com.app.al.wifi.util.WifiUtils
import com.app.al.wifi.view.fragment.base.BaseFragment
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/*
 * Wifi一覧Fragment
 */
class WifiListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

  private val TAG = WifiListFragment::class.simpleName!!

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: WifiListAdapter
  private lateinit var swipeRefreshLayout: SwipeRefreshLayout
  private var disposable: Disposable? = null

  companion object {
    /**
     * インスタンス生成
     *
     * @return Wifi一覧Fragment
     */
    fun newInstance(): WifiListFragment = WifiListFragment()
  }

  /**
   * onCreateView
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_wifi_list, container, false)
    // TODO DataBindingが動作しないので暫定対応
    swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
    swipeRefreshLayout.setOnRefreshListener(this)
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
   * onDestroy
   */
  override fun onDestroy() {
    super.onDestroy()
    disposable?.dispose()
  }

  /**
   * Wifi一覧の更新
   */
  override fun onRefresh() {
    Log.d(TAG, "onRefresh")
    adapter.reload(WifiUtils.getWifiInformationList(activity))
    swipeRefreshLayout.isRefreshing = false
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
    initPermission()
  }

  /**
   * 権限初期処理
   */
  private fun initPermission() {
    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || PermissionUtils.isRequestPermission(this, ApplicationConst.PERMISSIONS)) {
      setAdapter()
    }
  }

  /**
   * Wifi一覧アダプタ設定
   */
  private fun setAdapter() {
    adapter = WifiListAdapter(context, WifiUtils.getWifiInformationList(activity))
    recyclerView.adapter = adapter
    disposable = adapter.clickEvent
        .compose(bindToLifecycle())
        .subscribe({
          WifiDialogFragment.newInstance(it).show(fragmentManager, "test")
        })
  }

  /**
   * EventBus Wifi接続
   *
   * @param event Wifi接続イベント
   */
  @Subscribe(threadMode = ThreadMode.POSTING)
  fun onWifiConnectEvent(event: WifiConnectEvent) {
    WifiUtils.connect(context!!, event.ssId, event.capabilities, event.password)
  }
}
