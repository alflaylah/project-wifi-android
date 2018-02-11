package com.app.al.wifi

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.app.al.wifi.viewmodel.dialog.WifiDialogViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * WifiダイアログViewModelテスト
 */
@RunWith(AndroidJUnit4::class)
class WifiDialogViewModelTest {

  @Test
  @Throws(Exception::class)
  fun connectEnabled() {
    val ssid = ""
    val capabilities = ""
    val wifiDialogViewModel = WifiDialogViewModel(InstrumentationRegistry.getContext(), ssid, capabilities)
    assertFalse(wifiDialogViewModel.isDoButtonEnabled.get())
    wifiDialogViewModel.password.set("test")
    assertTrue(wifiDialogViewModel.isDoButtonEnabled.get())
  }
}
