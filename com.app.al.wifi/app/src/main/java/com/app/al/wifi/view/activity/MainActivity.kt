package com.app.al.wifi.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.R.id
import com.app.al.wifi.R.layout.activity_main
import com.app.al.wifi.view.fragment.WifiListFragment

/**
 * Main画面Activity
 */
class MainActivity : AppCompatActivity() {

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
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.fragment_container, WifiListFragment.newInstance())
    transaction.addToBackStack(null)
    transaction.commit()
  }
}
