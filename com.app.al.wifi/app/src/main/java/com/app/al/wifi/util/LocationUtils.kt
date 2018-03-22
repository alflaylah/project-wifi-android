package com.app.al.wifi.util

import android.app.Activity
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.location.LocationManager
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.app.al.wifi.const.ApplicationConst
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes

/**
 * 位置情報ユーティリティ
 */
object LocationUtils {

  private val TAG = LocationUtils::class.simpleName!!
  const val REQUEST_CODE = ApplicationConst.REQUEST_LOCATION

  /**
   * 位置情報ON/OFFの確認
   *
   * @param activity 遷移元Activity
   */
  fun checkLocation(activity: FragmentActivity) {
    val locationManager = activity.getSystemService(LOCATION_SERVICE) as LocationManager
    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
      // 位置情報がOFFの場合、位置情報を許可するダイアログを表示
      requestLocation(activity)
    }
  }

  /**
   * 位置情報ON/OFFの確認
   *
   * @param activity 遷移元Activity
   */
  private fun requestLocation(activity: FragmentActivity) {
    val locationRequest = LocationRequest.create()
    locationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
    // 位置情報OFFの場合、常に許可ダイアログを開くようにする
    builder.setAlwaysShow(true)
    val task = LocationServices.getSettingsClient(activity)
        .checkLocationSettings(builder.build())
    task.addOnCompleteListener {
      try {
        // 位置情報がON、何もしない
        val response = task.getResult(ApiException::class.java)
        Log.d(TAG, response.toString())
      } catch (exception: ApiException) {
        when (exception.statusCode) {
          LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
            // 位置情報がOFF、位置情報許可ダイアログを表示
            val resolvable = exception as ResolvableApiException
            resolvable.startResolutionForResult(activity, REQUEST_CODE)
          } catch (sendIntentException: SendIntentException) {
            Log.d(TAG, sendIntentException.message)
          } catch (classCastException: ClassCastException) {
            Log.d(TAG, classCastException.message)
          }
          LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
            // 位置情報が無効？
          }
          else -> {
            // その他
          }
        }
      }
    }
  }

  /**
   * 位置情報許可/不許可の結果確認
   *
   * @param requestCode requestCode
   * @param resultCode resultCode
   * @param intent intent
   * @return true：許可 false：不許可
   */
  fun isRequestLocationResult(
    requestCode: Int,
    resultCode: Int,
    intent: Intent
  ): Boolean {
    var result = false
    when (requestCode) {
      REQUEST_CODE ->
        when (resultCode) {
          Activity.RESULT_OK -> {
            result = true
          }
          Activity.RESULT_CANCELED -> {
          }
          else -> {
          }
        }
    }
    return result
  }
}
