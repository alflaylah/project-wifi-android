package com.app.al.wifi.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 ApplicationModuleクラス
 *
 * @param application Application
 */
@Module
class ApplicationModule(private val application: Application) {

  @Provides
  @Singleton
  internal fun provideApplicationContext(): Context {
    return application.applicationContext
  }
}