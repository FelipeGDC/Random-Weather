package com.fgdc.randomweather.di.module

import android.app.Activity
import com.fgdc.randomweather.di.scope.ViewScope
import com.fgdc.randomweather.ui.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ViewModule(private val activity: BaseActivity) {

    @Provides
    @ViewScope
    internal fun activity(): Activity {
        return activity
    }
}
