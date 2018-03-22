package com.app.al.wifi.view.fragment

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.app.al.wifi.R
import com.app.al.wifi.ui.ada.WifiListAdapter
import com.app.al.wifi.util.WifiUtils
import com.app.al.wifi.view.dialog.WifiDialogFragment
import com.app.al.wifi.view.fragment.base.BaseFragment
import io.reactivex.disposables.Disposable

/*
 * Wifi一覧画面Fragment
 */
class WifiListFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: WifiListAdapter
  private lateinit var swipeRefreshLayout: SwipeRefreshLayout
  private lateinit var emptyView: LinearLayout
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
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_list_wifi, container, false)
    // TODO 暫定、DataBindingしたい
    swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
    swipeRefreshLayout.setOnRefreshListener(this)
    emptyView = view.findViewById(R.id.empty_view)
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
   * onResume
   */
  override fun onResume() {
    super.onResume()
    onRefresh()
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
    adapter.reload(WifiUtils.getWifiList(context))
    emptyView.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
    swipeRefreshLayout.isRefreshing = false
  }

  /**
   * 初期処理
   */
  private fun init() {
    initAdapter()
  }

  /**
   * Wifi一覧画面アダプタ設定
   */
  private fun initAdapter() {
    adapter = WifiListAdapter(context, WifiUtils.getWifiList(context))
    recyclerView.adapter = adapter
    disposable = adapter.clickEvent
        .subscribe({
          WifiDialogFragment.newInstance(it)
              .show(fragmentManager, "test")
        })

  }
}
