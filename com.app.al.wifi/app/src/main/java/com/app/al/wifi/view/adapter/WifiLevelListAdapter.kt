package com.app.al.wifi.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * WifiダイアログSpinnerアダプタ
 *
 * @param context applicationContext
 * @param wifiList Wifi一覧
 */
class WifiLevelListAdapter(private val cont: Context, private val layoutID: Int, private val names: Array<String>, private val images: Array<String>) : BaseAdapter() {

  private val inflater: LayoutInflater
  private val imageIDs: IntArray
  private val res: Resources

  internal class ViewHolder {
    var imageView: ImageView? = null
    var textView: TextView? = null
  }

  init {
    inflater = LayoutInflater.from(cont)
    imageIDs = IntArray(images.size)
    res = cont.resources

    // 最初に画像IDを配列で取っておく
    for (i in images.indices) {
      imageIDs[i] = res.getIdentifier(images[i], "drawable", cont.packageName)
    }
  }


  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    var convertView = convertView

    val holder: ViewHolder
    if (convertView == null) {
      convertView = inflater.inflate(layoutID, null)
      holder = ViewHolder()
      //holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
      //holder.textView = (TextView) convertView.findViewById(R.id.text_view);
      convertView!!.tag = holder
    } else {
      holder = convertView.tag as ViewHolder
    }

    holder.imageView!!.setImageResource(imageIDs[position])
    holder.textView!!.text = names[position]

    return convertView
  }

  override fun getCount(): Int {
    return names.size
  }

  override fun getItem(position: Int): Any {
    return position
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

}
