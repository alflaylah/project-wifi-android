package com.app.al.wifi.ui.activity

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.app.al.wifi.R
import com.app.al.wifi.R.id
import com.app.al.wifi.R.layout.activity_main
import com.app.al.wifi.util.NetworkUtils

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(activity_main)
    val toolbar = findViewById<Toolbar>(id.toolbar) as Toolbar
    setSupportActionBar(toolbar)

    val fab = findViewById<FloatingActionButton>(id.fab) as FloatingActionButton
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }
    var wifiInformationList = NetworkUtils.getWifiInformationList(this)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId
    if (id == R.id.action_settings) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}
