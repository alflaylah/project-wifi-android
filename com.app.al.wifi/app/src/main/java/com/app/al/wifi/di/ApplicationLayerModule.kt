package com.app.al.wifi.di

import android.content.Context
import com.app.al.wifi.viewmodel.WifiListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 ApplicationLayerModuleクラス
 */
@Module
class ApplicationLayerModule {

  @Provides
  @Singleton
  fun provideWifiListViewModel(context: Context): WifiListViewModel {
    return WifiListViewModel(context)
  }
}