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
import com.app.al.wifi.viewmodel.fragment.WifiListViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Wifi一覧画面アダプタ
 *
 * @param context Context
 * @param wifiList Wifi一覧
 */
class WifiListAdapter(
  private var context: Context?,
  private var wifiList: List<ScanResult>
) : RecyclerView.Adapter<WifiListAdapter.ViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)
  private val publishSubject = PublishSubject.create<WifiListViewModel>()
  val clickEvent: Observable<WifiListViewModel> = publishSubject

  /**
   * onCreateViewHolder
   *
   * @param viewGroup viewGroup
   * @param i 位置
   */
  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    i: Int
  ): WifiListAdapter.ViewHolder = ViewHolder(inflater.inflate(R.layout.list_item_wifi, viewGroup, false))

  /**
   * onBindViewHolder
   *
   * @param viewHolder viewHolder
   * @param i 位置
   */
  override fun onBindViewHolder(
    viewHolder: ViewHolder,
    i: Int
  ) {
    viewHolder.bind(wifiList[i])
  }

  /**
   * getItemCount
   *
   * @return ItemCount
   */
  override fun getItemCount(): Int = wifiList.size

  /**
   * Wifi一覧更新
   *
   * @param wifiList Wifi一覧
   */
  fun reload(wifiList: List<ScanResult>) {
    this.wifiList = wifiList
    this.notifyDataSetChanged()
  }

  /**
   * ViewHolderクラス
   *
   * @param itemView itemView
   */
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    private lateinit var wifiListViewModel: WifiListViewModel

    init {
      // Item押下時のイベントを定義
      itemView.setOnClickListener {
        publishSubject.onNext(wifiListViewModel)
      }
    }

    /**
     * バインド
     *
     * @param
     */
    fun bind(scanResult: ScanResult) {
      wifiListViewModel = WifiListViewModel(context, scanResult)
      binding?.setVariable(BR.viewModel, wifiListViewModel)
      binding?.executePendingBindings()
    }
  }
}