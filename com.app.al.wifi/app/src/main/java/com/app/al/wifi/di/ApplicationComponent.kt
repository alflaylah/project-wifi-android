package com.app.al.wifi.di;

import com.app.al.wifi.view.activity.MainActivity
import com.app.al.wifi.view.dialog.ConfirmDialogFragment
import com.app.al.wifi.view.dialog.base.BaseDialogFragment
import com.app.al.wifi.view.fragment.base.BaseFragment
import com.app.al.wifi.viewmodel.fragment.WifiListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 ApplicationComponentクラス
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (ApplicationLayerModule::class)])
interface ApplicationComponent {

  /**
   * Activity
   */
  fun inject(activity: MainActivity)

  /**
   * Fragment
   */
  fun inject(fragment: BaseFragment)

  fun inject(dialogFragment: BaseDialogFragment)

  fun inject(dialogFragment: ConfirmDialogFragment)

  /**
   * ViewModel
   */
  fun inject(viewModel: WifiListViewModel)
}