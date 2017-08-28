package com.app.al.wifi.ui.fragment

import android.content.Context
import android.net.wifi.ScanResult
import android.os.Bundle
import android.support.annotation.MainThread
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.R
import com.app.al.wifi.ui.ada.WifiAdapter
import com.app.al.wifi.ui.event.OnWifiEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/*
 * Wifi一覧表示Fragment
 */
class WifiFragment : Fragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: WifiAdapter

  companion object {
    /**
     * インスタンス生成
     *
     * @return Main画面Fragment
     */
    fun newInstance(wifiInformationList: ArrayList<ScanResult>): WifiFragment {
      var wifiFragment = WifiFragment()
      var bundle: Bundle = Bundle()
      bundle.putParcelableArrayList("", wifiInformationList)
      return wifiFragment
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
    val view = inflater!!.inflate(R.layout.fragment_wifi, container, false)
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
    val array = mutableListOf("1", "2", "3") as List<String>

    adapter = WifiAdapter(activity, array)
    recyclerView!!.adapter = adapter
  }

  /**
   *
   */
  @Subscribe(threadMode = ThreadMode.MAIN)
  fun onWifiClicked(onWifiEvent: OnWifiEvent) {
    onWifiEvent.getView()
    onWifiEvent.getPosition()
  }
}
