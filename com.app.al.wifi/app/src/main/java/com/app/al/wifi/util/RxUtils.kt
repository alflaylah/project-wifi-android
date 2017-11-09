package com.app.al.wifi.util

import android.databinding.Observable.OnPropertyChangedCallback
import android.databinding.ObservableField
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable


/**
 * Reactiveユーティリティ
 */
object RxUtils {

  /**
   * ObservableField -> Flowable変換
   *
   * @param observableField ObservableField
   * @return Flowable
   */
  fun <T> toFlowable(observableField: ObservableField<T>): Flowable<T> {
    return Flowable.create({ emitter ->
      val callback = object : OnPropertyChangedCallback() {
        override fun onPropertyChanged(dataBindingObservable: android.databinding.Observable, propertyId: Int) {
          if (dataBindingObservable === observableField) {
            emitter.onNext(observableField.get())
          }
        }
      }
      observableField.addOnPropertyChangedCallback(callback)
      emitter.setCancellable({ observableField.removeOnPropertyChangedCallback(callback) })
    }, BackpressureStrategy.LATEST)
  }
}