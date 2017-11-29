package com.app.al.wifi.event.bus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by yugiki on 2017/11/29.
 */
object RxEventBus {

  // subscriber and observable
  private val subject = PublishSubject.create<Any>()

  // イベントを流す
  fun post(event: Any) {
    subject.onNext(event)
  }

  // ストリームを filter して購読対象を絞れるようにする
  fun <T> stream(event: Class<T>): Observable<T> = subject.hide().ofType(event)
}