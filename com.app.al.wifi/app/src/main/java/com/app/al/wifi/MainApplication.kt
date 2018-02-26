package com.app.al.wifi

import android.app.Application
import com.app.al.wifi.di.ApplicationComponent
import com.app.al.wifi.di.ApplicationModule

/**
 * Applicationクラス
 */
class MainApplication : Application() {

  private lateinit var applicationComponent: ApplicationComponent

  /**
   * onCreate
   */
  override fun onCreate() {
    super.onCreate()
    init()
  }

  /**
   * 初期処理
   */
  private fun init() {
    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  /**
   * Dagger2 ApplicationComponent返却
   *
   * @return ApplicationComponent
   */
  fun getApplicationComponent(): ApplicationComponent = applicationComponent
}
