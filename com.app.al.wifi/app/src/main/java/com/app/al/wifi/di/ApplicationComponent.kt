package com.app.al.wifi.di;

import com.app.al.wifi.view.fragment.WifiListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 ApplicationComponentクラス
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApplicationLayerModule::class))
interface ApplicationComponent {

  fun inject(fragment: WifiListFragment)
}