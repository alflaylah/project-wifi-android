package com.app.al.wifi.di;

import com.app.al.wifi.view.activity.MainActivity
import com.app.al.wifi.view.activity.base.BaseActivity
import com.app.al.wifi.view.fragment.ConfirmDialogFragment
import com.app.al.wifi.view.fragment.base.BaseDialogFragment
import com.app.al.wifi.view.fragment.base.BaseFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 ApplicationComponentクラス
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ApplicationLayerModule::class))
interface ApplicationComponent {

  /**
   * Activity
   */
  fun inject(activity: BaseActivity)

  fun inject(activity: MainActivity)

  /**
   * Fragment
   */
  fun inject(fragment: BaseFragment)

  fun inject(dialogFragment: BaseDialogFragment)

  fun inject(dialogFragment: ConfirmDialogFragment)
}