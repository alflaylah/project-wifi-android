package com.app.al.wifi.ui.ada

import android.content.Context
import android.net.wifi.ScanResult
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.al.wifi.R
import com.app.al.wifi.view.event.WifiListEvent
import org.greenrobot.eventbus.EventBus


/**
 * Wifi一覧アダプタ
 */
class WifiListAdapter(context: Context, private val wifiInformationList: List<ScanResult>?) : RecyclerView.Adapter<WifiListAdapter.ViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  /**
   * onCreateViewHolder
   *
   * @param viewGroup viewGroup
   * @param i 位置
   */
  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WifiListAdapter.ViewHolder = ViewHolder(inflater.inflate(R.layout.list_item_wifi, viewGroup, false))

  /**
   * onBindViewHolder
   *
   * @param viewHolder viewHolder
   * @param i 位置
   */
  override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
    if (wifiInformationList != null && wifiInformationList.size > i) {
      viewHolder.textView.text = wifiInformationList[i].SSID
    }
    viewHolder.itemView.setOnClickListener({ v -> EventBus.getDefault().post(WifiListEvent(v, i)) })
  }

  /**
   * getItemCount
   *
   * @return ItemCount
   */
  override fun getItemCount(): Int = wifiInformationList?.size ?: 0

  /**
   * ViewHolderクラス
   *
   * @param itemView itemView
   */
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.list_item_text)
  }
}