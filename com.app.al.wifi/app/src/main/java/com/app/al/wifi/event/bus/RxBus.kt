package com.app.al.wifi.event.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * イベント発行・購読
 */
class RxBus {

  private var publishSubject = PublishSubject.create<Any>().toSerialized()

  /**
   * Observable返却
   *
   * @param event 発行イベント
   * @return Observable
   */
  fun <T> toObservable(event: Class<T>): Observable<T> = publishSubject.hide().ofType(event).retry()

  /**
   * イベント発行
   *
   * @param event イベント
   */
  fun post(event: Any) {
    publishSubject.onNext(event)
  }

  /**
   * イベント発行可否
   */
  fun hasObservers(): Boolean = publishSubject.hasObservers()
}
