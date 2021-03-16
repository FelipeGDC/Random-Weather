package com.fgdc.randomweather

import android.app.Application
import com.fgdc.randomweather.di.component.ApplicationComponent
import com.fgdc.randomweather.di.component.DaggerApplicationComponent
import com.fgdc.randomweather.di.module.ApplicationModule

class RandomWeatherApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
