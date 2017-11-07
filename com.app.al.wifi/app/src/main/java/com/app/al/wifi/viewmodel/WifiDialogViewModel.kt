package com.app.al.wifi.viewmodel

import android.view.View
import com.app.al.wifi.event.CloseDialogEvent
import com.app.al.wifi.viewmodel.base.BaseDialogViewModel
import org.greenrobot.eventbus.EventBus

/**
 * WifiダイアログViewModel
 *
 * @param ssId ssId
 */
class WifiDialogViewModel(val ssId: String) : BaseDialogViewModel()