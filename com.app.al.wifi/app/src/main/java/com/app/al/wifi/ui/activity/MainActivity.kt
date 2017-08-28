package com.app.al.wifi.ui.activity

import android.net.wifi.ScanResult
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.R.id
import com.app.al.wifi.R.layout.activity_main
import com.app.al.wifi.common.Constant
import com.app.al.wifi.ui.fragment.WifiFragment
import com.app.al.wifi.util.NetworkUtils
import com.app.al.wifi.util.PermissionUtils

/**
 * Main画面Activity
 */
class MainActivity : AppCompatActivity() {

  // WIFI情報リスト
  private var wifiInformationList = arrayListOf<ScanResult>()

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(activity_main)
    init()
  }

  /**
   * onCreateOptionsMenu
   *
   * @param menu menu
   */
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  /**
   * onOptionsItemSelected
   *
   * @param item item
   */
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == R.id.action_settings) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }

  /**
   * 権限要求結果
   *
   * @param requestCode requestCode
   * @param permissions permissions
   * @param grantResults grantResults
   */
  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
    when (requestCode) {
      PermissionUtils.REQUEST_PERMISSION -> if (PermissionUtils.isRequestPermissionResult(grantResults)) {
        // 許可された
        wifiInformationList = NetworkUtils.getWifiInformationList(this)
        initFragment()
      } else {
        // 許可されなかった
        PermissionUtils.checkNeverRequestPermission(this, permissions, R.string.permission_denied_message)
      }
      else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
  }

  /**
   * 初期処理
   */
  private fun init() {
    initPart()
    initPermission()
  }

  /**
   * パーツ初期処理
   */
  private fun initPart() {
    val toolbar = findViewById<Toolbar>(id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
  }

  /**
   * 権限初期処理
   */
  private fun initPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      if (PermissionUtils.isRequestPermission(this, Constant.PERMISSIONS)) {
        wifiInformationList = NetworkUtils.getWifiInformationList(this)
      }
    } else {
      wifiInformationList = NetworkUtils.getWifiInformationList(this)
    }
    initFragment()
  }

  /**
   * フラグメント初期処理
   */
  private fun initFragment() {
    if (wifiInformationList.isNotEmpty()) {
      val fragmentManager = supportFragmentManager
      val transaction = fragmentManager.beginTransaction()
      transaction.replace(R.id.fragment_container, WifiFragment.newInstance(wifiInformationList))
      transaction.addToBackStack(null)
      transaction.commit()
    }
  }
}
