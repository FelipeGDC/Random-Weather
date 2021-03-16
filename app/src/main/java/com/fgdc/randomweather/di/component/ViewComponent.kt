package com.fgdc.randomweather.di.component

import com.fgdc.randomweather.di.module.ViewModule
import com.fgdc.randomweather.di.scope.ViewScope
import com.fgdc.randomweather.ui.MainActivity
import com.fgdc.randomweather.ui.weather.fragments.WeatherFragment
import dagger.Subcomponent

@ViewScope
@Subcomponent(
    modules = [
        ViewModule::class
    ]
)
interface ViewComponent {

    fun inject(activity: MainActivity)

    fun inject(weatherFragment: WeatherFragment)
}
