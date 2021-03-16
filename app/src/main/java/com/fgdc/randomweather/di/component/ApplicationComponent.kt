package com.fgdc.randomweather.di.component

import com.fgdc.randomweather.RandomWeatherApplication
import com.fgdc.randomweather.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        DataModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: RandomWeatherApplication)

    fun viewComponent(viewModule: ViewModule): ViewComponent
}
