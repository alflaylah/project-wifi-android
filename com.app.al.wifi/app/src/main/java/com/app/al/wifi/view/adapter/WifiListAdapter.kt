package com.app.al.wifi.ui.ada

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.net.wifi.ScanResult
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.BR
import com.app.al.wifi.R
import com.app.al.wifi.viewmodel.WifiViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Wifi一覧アダプタ
 */
class WifiListAdapter(context: Context, private val wifiInformationList: List<ScanResult>) : RecyclerView.Adapter<WifiListAdapter.ViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)
  private val publishSubject = PublishSubject.create<WifiViewModel>()
  val clickEvent: Observable<WifiViewModel> = publishSubject

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
    viewHolder.bind(wifiInformationList[i])
  }

  /**
   * getItemCount
   *
   * @return ItemCount
   */
  override fun getItemCount(): Int = wifiInformationList.size

  /**
   * ViewHolderクラス
   *
   * @param itemView itemView
   */
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewDataBinding = DataBindingUtil.bind(itemView)
    private lateinit var wifiListItemViewModel: WifiViewModel

    init {
      // Item押下時のイベントを定義
      itemView.setOnClickListener {
        publishSubject.onNext(wifiListItemViewModel)
      }
    }

    /**
     * バインド
     */
    fun bind(scanResult: ScanResult) {
      wifiListItemViewModel = WifiViewModel(scanResult)
      binding.setVariable(BR.wifiItem, wifiListItemViewModel)
      binding.executePendingBindings()
    }
  }
}