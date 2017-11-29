package com.app.al.wifi.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.const.ApplicationConst
import com.app.al.wifi.event.CloseEvent
import com.app.al.wifi.event.CloseEvent.Companion.CloseType.APPLICATION
import com.app.al.wifi.event.RxBus
import com.app.al.wifi.event.StartActivityEvent
import com.app.al.wifi.event.WifiEvent
import com.app.al.wifi.util.ApplicationUtils
import com.app.al.wifi.util.LocationUtils
import com.app.al.wifi.util.PermissionUtils
import com.app.al.wifi.view.activity.base.BaseActivity
import com.app.al.wifi.view.fragment.ConfirmDialogFragment
import com.app.al.wifi.view.fragment.WifiListFragment
import com.app.al.wifi.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * Main画面Activity
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

  private val TAG = MainActivity::class.simpleName!!

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
      // ナビゲーションドロワー閉じます
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      if (fragmentManager.backStackEntryCount == 0) {
        // アプリ終了確認
        ConfirmDialogFragment.newInstance(getString(R.string.close_application_message), getString(R.string.close), getString(R.string.not_close)).show(supportFragmentManager, TAG)
      } else {
        // 前画面へ
        fragmentManager.popBackStack()
      }
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
   * onActivityResult
   *
   * @param requestCode requestCode
   * @param resultCode resultCode
   * @param intent intent
   */
  override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
    when (requestCode) {
      LocationUtils.REQUEST_CODE -> if (!LocationUtils.isRequestLocationResult(requestCode, resultCode, intent)) {
        // TODO
      }
      else -> {
        super.onActivityResult(requestCode, resultCode, intent)
      }
    }
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
      PermissionUtils.REQUEST_CODE -> {
        if (!PermissionUtils.isRequestPermissionResult(grantResults)) {
          // 許可されました
          LocationUtils.checkLocation(this)
        } else {
          // 許可されませんでした
          PermissionUtils.checkNeverRequestPermission(this, permissions, R.string.permission_denied_message)
        }
      }
      else -> {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
      }
    }
  }

  /**
   * 初期処理
   */
  private fun init() {
    getApplicationComponent().inject(this)
    compositeDisposable.add(RxBus.toObservable(CloseEvent::class.java)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ onCloseEvent(it) })
    )
    initToolBar(R.string.title_main)
    initDrawerLayout()
    initFragment()
    initPermission()
  }

  /**
   * ナビゲーションドロワー初期処理
   */
  private fun initDrawerLayout() {
    drawerLayout = findViewById(R.id.drawer_layout)
    val navigationView = findViewById<NavigationView>(R.id.navigation_view)
    val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    navigationView.setNavigationItemSelectedListener(this)
    drawerLayout.addDrawerListener(actionBarDrawerToggle)
  }

  /**
   * 権限初期処理
   */
  private fun initPermission() {
    if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M)) {
      // 6.0より下のバージョンは権限チェック不要なので、位置情報の確認へ
      LocationUtils.checkLocation(this)
      return
    }
    if (PermissionUtils.isRequestPermission(this, ApplicationConst.PERMISSIONS)) {
      // 権限が許可済みの場合、位置情報の確認へ
      LocationUtils.checkLocation(this)
    }
  }

  /**
   * フラグメント初期処理
   */
  private fun initFragment() {
    replaceFragment(WifiListFragment.newInstance())
  }

  /**
   * 画面遷移
   *
   * @param event 画面遷移イベント
   */
  @Subscribe(threadMode = ThreadMode.POSTING)
  fun onStartActivityEvent(event: StartActivityEvent) {
    val bundle = Bundle()
    when (event.id) {
      R.id.menu_license -> {
        // ライセンス画面を開きます
        bundle.putString(ApplicationConst.BUNDLE_URL, getString(R.string.url_license))
        ApplicationUtils.startActivity(this, WebActivity::class.java, bundle)
      }
      StartActivityEvent.OS_SETTING -> {
        // 権限付与の為、設定画面を開きます。
        ApplicationUtils.startApplicationDetailSettings(this)
      }
    }
  }

  /**
   * アプリケーション終了
   *
   * @param event クローズイベント
   */
  fun onCloseEvent(event: CloseEvent) {
    when (event.closeType) {
      APPLICATION -> {
        finish()
      }
      else -> {
      }
    }
  }

  /**
   * Wifi接続メッセージ取得
   *
   * @param event Wifi関連イベント
   */
  @Subscribe(threadMode = ThreadMode.POSTING)
  fun onWifiReceiverEvent(event: WifiEvent) {
    if (!event.message.isNullOrEmpty()) {
      Snackbar.make(findViewById(R.id.linear_layout), event.message.toString(), Snackbar.LENGTH_LONG).show()
    }
  }
}
