package com.app.al.wifi.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.ui.ada.EtcListAdapter
import com.app.al.wifi.util.StringUtils
import com.app.al.wifi.view.fragment.base.BaseFragment
import io.reactivex.disposables.Disposable

/*
 * その他一覧画面Fragment
 */
class EtcListFragment : BaseFragment() {

  private val TAG = EtcListFragment::class.simpleName!!

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: EtcListAdapter
  private var disposable: Disposable? = null

  companion object {
    /**
     * インスタンス生成
     *
     * @return その他一覧画面Fragment
     */
    fun newInstance(): EtcListFragment = EtcListFragment()
  }

  /**
   * onCreateView
   *
   * @param inflater inflater
   * @param container container
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_list_etc, container, false)
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
   * onDestroy
   */
  override fun onDestroy() {
    super.onDestroy()
    disposable?.dispose()
  }

  /**
   * 初期処理
   */
  private fun init() {
    initAdapter()
  }

  /**
   * その他一覧画面アダプタ設定
   */
  private fun initAdapter() {
    adapter = EtcListAdapter(context, StringUtils.getStringArrayResource(context, R.array.etc_item))
    recyclerView.adapter = adapter
    disposable = adapter.clickEvent
        .subscribe({
          it.onItemClicked()
        })
  }

}
