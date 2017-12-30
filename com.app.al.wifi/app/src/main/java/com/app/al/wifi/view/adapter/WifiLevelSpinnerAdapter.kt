package com.app.al.wifi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst.WifiLevel

/**
 * Wi-Fi電波強度スピナーアダプタ
 */
class WifiLevelSpinnerAdapter internal constructor(private val context: Context?, private val layoutID: Int) : BaseAdapter() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    var convertView = convertView

    val holder: ViewHolder
    if (convertView == null) {
      convertView = inflater.inflate(layoutID, null)
      holder = ViewHolder()

      holder.imageView = convertView!!.findViewById(R.id.image_view)
      holder.textView = convertView.findViewById(R.id.text_view)
      convertView.tag = holder
    } else {
      holder = convertView.tag as ViewHolder
    }

    holder.imageView!!.setImageResource(context?.resources?.getIdentifier(WifiLevel.values()[position].resourceName, "mipmap", context.packageName)!!)
    holder.textView!!.text = WifiLevel.values()[position].status

    return convertView
  }

  /**
   * 件数返却
   *
   * @return 件数
   */
  override fun getCount(): Int = WifiLevel.values().size

  /**
   * 件数返却
   *
   * @return 件数
   */
  override fun getItem(position: Int): Any = position

  /**
   * 件数返却
   *
   * @return 件数
   */
  override fun getItemId(position: Int): Long = position.toLong()

  internal class ViewHolder {
    var imageView: ImageView? = null
    var textView: TextView? = null
  }

}