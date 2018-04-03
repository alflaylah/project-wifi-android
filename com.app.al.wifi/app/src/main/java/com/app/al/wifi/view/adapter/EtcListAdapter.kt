package com.app.al.wifi.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.al.wifi.BR
import com.app.al.wifi.R
import com.app.al.wifi.util.ApplicationUtils
import com.app.al.wifi.viewmodel.fragment.EtcListViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * その他一覧画面アダプタ
 *
 * @param context Context
 * @param etcList その他一覧
 */
class EtcListAdapter(
  private var context: Context?,
  private var etcList: List<String>
) : RecyclerView.Adapter<EtcListAdapter.ViewHolder>() {

  private val inflater: LayoutInflater = LayoutInflater.from(context)
  private val publishSubject = PublishSubject.create<EtcListViewModel>()
  val clickEvent: Observable<EtcListViewModel> = publishSubject

  /**
   * onCreateViewHolder
   *
   * @param viewGroup viewGroup
   * @param i 位置
   */
  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    i: Int
  ): EtcListAdapter.ViewHolder {
    return ViewHolder(inflater.inflate(R.layout.list_item_etc, viewGroup, false))
  }

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
    viewHolder.bind(etcList[i])
  }

  /**
   * getItemCount
   *
   * @return ItemCount
   */
  override fun getItemCount(): Int = etcList.size

  /**
   * ViewHolder
   *
   * @param itemView itemView
   */
  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    private lateinit var viewModel: EtcListViewModel

    init {
      // Item押下時のイベントを定義
      itemView.setOnClickListener {
        publishSubject.onNext(viewModel)
      }
    }

    /**
     * バインド
     *
     * @param
     */
    fun bind(title: String) {
      viewModel = if (title.contains(context?.getString(R.string.version).toString())) {
        // バージョン
        EtcListViewModel(title, ApplicationUtils.getVersionName(context))
      } else {
        // バージョン以外
        EtcListViewModel(title)
      }
      binding?.setVariable(BR.viewModel, viewModel)
      binding?.executePendingBindings()
    }
  }
}