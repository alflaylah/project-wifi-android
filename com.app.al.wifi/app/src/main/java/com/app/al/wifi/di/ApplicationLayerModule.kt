package com.app.al.wifi.di

import com.app.al.wifi.viewmodel.ConfirmDialogViewModel
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
  fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

  /**
   * ViewModel
   */
  @Provides
  @Singleton
  fun provideMainViewModel(): MainViewModel = MainViewModel()

  @Provides
  @Singleton
  fun provideConfirmDialogViewModel(): ConfirmDialogViewModel = ConfirmDialogViewModel()
}