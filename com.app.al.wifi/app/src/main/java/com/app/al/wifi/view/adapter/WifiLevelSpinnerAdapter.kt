package com.app.al.wifi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.const.ApplicationConst.WifiLevel

/**
 * Wi-Fi電波強度スピナーアダプタ
 *
 * @param context Context
 * @param resId リソースID
 */
class WifiLevelSpinnerAdapter(private val context: Context?, private val resId: Int) : BaseAdapter() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  /**
   * getView
   *
   * @param position position
   * @param convertView convertView
   * @param parent parent
   */
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
    var convertView = convertView
    val viewHolder: ViewHolder
    if (convertView == null) {
      convertView = inflater.inflate(resId, null)
      viewHolder = ViewHolder()
      viewHolder.imageView = convertView.findViewById(R.id.level)
      viewHolder.textView = convertView.findViewById(R.id.level_description)
      convertView.tag = viewHolder
    } else {
      viewHolder = convertView.tag as ViewHolder
    }
    val resId = context?.resources?.getIdentifier(WifiLevel.values()[position].resourceName, ApplicationConst.MIPMAP, context.packageName)
    if (resId != null) {
      viewHolder.imageView.setImageResource(resId)
    }
    viewHolder.textView.text = WifiLevel.values()[position].status
    return convertView
  }

  /**
   * 件数返却
   *
   * @return 件数
   */
  override fun getCount(): Int = WifiLevel.values().size

  /**
   * アイテム返却
   *
   * @return WifiLevel
   */
  override fun getItem(position: Int): WifiLevel = WifiLevel.values()[position]

  /**
   * アイテムID返却
   *
   * @return WifiLevel.hashCode
   */
  override fun getItemId(position: Int): Long = WifiLevel.values()[position].hashCode().toLong()

  /**
   * ViewHolder
   */
  internal class ViewHolder {
    lateinit var imageView: ImageView
    lateinit var textView: TextView
  }
}