package com.fgdc.randomweather.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.fgdc.randomweather.R
import com.fgdc.randomweather.databinding.ActivityMainBinding
import com.fgdc.randomweather.di.component.ViewComponent
import com.fgdc.randomweather.ui.base.BaseActivity
import com.fgdc.randomweather.utils.extensions.empty

class MainActivity : BaseActivity() {
    override fun initializeInjector(viewComponent: ViewComponent) = viewComponent.inject(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = when (destination.id) {
                R.id.weatherView -> destination.label
                else -> String.empty()
            }

            binding.toolbar.visibility = when (destination.id) {
                else -> View.GONE
            }
        }
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun showProgressStatus(viewStatus: Int) {
        binding.progress.visibility = viewStatus
    }
}
