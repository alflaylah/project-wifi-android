package com.app.al.wifi.view.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Menu
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.R.id
import com.app.al.wifi.R.layout.activity_main
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.util.ApplicationUtils
import com.app.al.wifi.view.activity.base.BaseActivity
import com.app.al.wifi.view.fragment.WifiListFragment


/**
 * Main画面Activity
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

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
   * @param menu メニュー
   * @return boolean
   */
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  /**
   * onNavigationItemSelected
   *
   * @param item メニューアイテム
   * @return boolean
   */
  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.menu_license -> {
        var bundle = Bundle()
        bundle.putString(ApplicationConst.BUNDLE_URL, getString(R.string.url_license))
        ApplicationUtils.startActivity(this, WebViewActivity::class.java, bundle)
      }
    }
    findViewById<DrawerLayout>(R.id.drawer_layout).closeDrawer(GravityCompat.START)
    return true
  }

  /**
   * 初期処理
   */
  private fun init() {
    initActionBar()
    initFragment()
  }

  /**
   * ActionBar初期処理
   */
  private fun initActionBar() {
    setSupportActionBar(findViewById(id.toolbar))
  }

  /**
   * フラグメント初期処理
   */
  private fun initFragment() {
    replaceFragment(WifiListFragment.newInstance())
  }
}
