package com.fgdc.randomweather.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fgdc.randomweather.di.component.ViewComponent
import com.fgdc.randomweather.RandomWeatherApplication
import com.fgdc.randomweather.di.module.ViewModule
import com.fgdc.randomweather.ui.MainActivity

abstract class BaseFragment : Fragment() {

    protected abstract fun initializeInjector(viewComponent: ViewComponent)

    private val fragmentComponent by lazy {
        (activity?.application as RandomWeatherApplication)
            .appComponent
            .viewComponent(ViewModule(activity as BaseActivity))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector(fragmentComponent)
    }

    protected fun showSpinner(show: Boolean?) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }

    private fun showProgress() = progressStatus(View.VISIBLE)

    private fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) {
                this.showProgressStatus(viewStatus)
            }
        }
}
