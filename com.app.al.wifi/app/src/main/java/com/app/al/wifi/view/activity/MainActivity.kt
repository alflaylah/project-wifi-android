package com.app.al.wifi.view.activity

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.event.StartActivityEvent
import com.app.al.wifi.util.ApplicationUtils
import com.app.al.wifi.view.activity.base.BaseActivity
import com.app.al.wifi.view.fragment.WifiListFragment
import com.app.al.wifi.viewmodel.MainViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * Main画面Activity
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

  private lateinit var drawerLayout: DrawerLayout

  @Inject
  lateinit var mainViewModel: MainViewModel

  /**
   * onCreate
   *
   * @param savedInstanceState savedInstanceState
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    init()
  }

  /**
   * onStart
   */
  override fun onStart() {
    super.onStart()
    EventBus.getDefault().register(this)
  }

  /**
   * onStop
   */
  override fun onStop() {
    super.onStop()
    EventBus.getDefault().unregister(this)
  }

  /**
   * バックキー押下
   */
  override fun onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  /**
   * ドロワーレイアウトメニュー押下
   *
   * @param item メニューアイテム
   * @return boolean
   */
  override fun onNavigationItemSelected(item: MenuItem): Boolean {

    mainViewModel.onNavigationItemSelected(item.itemId)
    drawerLayout.closeDrawer(GravityCompat.START)
    return true
  }

  /**
   * 初期処理
   */
  private fun init() {
    getApplicationComponent().inject(this)
    initToolBar(R.string.title_main)
    initDrawerLayout()
    initFragment()
  }

  /**
   * ナビゲーションドロワー初期処理
   */
  private fun initDrawerLayout() {
    drawerLayout = findViewById(R.id.drawer_layout)
    val navigationView = findViewById<NavigationView>(R.id.navigation_view)
    val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
        getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    navigationView.setNavigationItemSelectedListener(this)
    drawerLayout.addDrawerListener(actionBarDrawerToggle)
  }

  /**
   * フラグメント初期処理
   */
  private fun initFragment() {
    replaceFragment(WifiListFragment.newInstance())
  }

  /**
   * EventBus 画面遷移
   *
   * @param event 画面遷移イベント
   */
  @Subscribe(threadMode = ThreadMode.POSTING)
  fun onStartActivityEvent(event: StartActivityEvent) {
    var bundle = Bundle()
    when (event.resId) {
      R.id.menu_license -> {
        bundle.putString(ApplicationConst.BUNDLE_URL, getString(R.string.url_license))
        ApplicationUtils.startActivity(this, WebActivity::class.java, bundle)
      }
    }
  }
}
