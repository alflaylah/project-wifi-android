package com.app.al.wifi

import android.content.ContentValues
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log

/*
 * Copyright DMM.com Lab Co.,Ltd.
 */
@BindingMethods(
    BindingMethod(type = SwipeRefreshLayout::class, attribute = "onRefresh", method = "setOnRefreshListener")
)
object Handlers {

  @JvmStatic
  fun onRefresh() {
    Log.d(ContentValues.TAG, "onRefresh")
  }
}