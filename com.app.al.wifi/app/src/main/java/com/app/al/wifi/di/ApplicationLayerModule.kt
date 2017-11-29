package com.app.al.wifi.di

import com.app.al.wifi.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Dagger2 ApplicationLayerModuleクラス
 */
@Module
class ApplicationLayerModule {

  @Provides
  @Singleton
  fun provideMainViewModel(): MainViewModel = MainViewModel()

  @Provides
  @Singleton
  fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}