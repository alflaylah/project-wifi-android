package com.app.al.wifi.event

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * イベント発行・購読
 */
object RxBus {

  private val publishSubject = PublishSubject.create<Any>()

  /**
   * Observable返却
   *
   * @return Observable
   */
  fun toObservable(): Observable<Any> = publishSubject

  /**
   * Observable返却
   *
   * @param clazz 発行イベントクラス
   * @return Observable
   */
  fun <T> toObservable(clazz: Class<T>): Observable<T> = publishSubject.cast(clazz).retry()

  /**
   * イベント発行可否
   */
  fun hasObservers(): Boolean = publishSubject.hasObservers()

  /**
   * イベント発行
   *
   * @param event イベント
   */
  fun send(event: Any) {
    publishSubject.onNext(event)
  }
}
