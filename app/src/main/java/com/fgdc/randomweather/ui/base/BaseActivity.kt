package com.fgdc.randomweather.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fgdc.randomweather.RandomWeatherApplication
import com.fgdc.randomweather.di.component.ViewComponent
import com.fgdc.randomweather.di.module.ViewModule

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initializeInjector(viewComponent: ViewComponent)

    private val activityComponent by lazy {
        (application as RandomWeatherApplication)
            .appComponent
            .viewComponent(ViewModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(activityComponent)
    }
}
