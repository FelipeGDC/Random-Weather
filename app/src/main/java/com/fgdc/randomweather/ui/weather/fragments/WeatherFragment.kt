package com.fgdc.randomweather.ui.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.fgdc.randomweather.R
import com.fgdc.randomweather.databinding.FragmentWeatherBinding
import com.fgdc.randomweather.di.component.ViewComponent
import com.fgdc.randomweather.ui.base.BaseFragment
import com.fgdc.randomweather.ui.weather.models.WeatherView
import com.fgdc.randomweather.ui.weather.viewmodel.WeatherViewModel
import com.fgdc.randomweather.utils.exception.ErrorHandler
import com.fgdc.randomweather.utils.extensions.empty
import com.fgdc.randomweather.utils.extensions.failure
import com.fgdc.randomweather.utils.extensions.observe
import java.util.*
import javax.inject.Inject

class WeatherFragment : BaseFragment() {
    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    override fun initializeInjector(viewComponent: ViewComponent) = viewComponent.inject(this)
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(weatherViewModel) {
            observe(showSpinner, ::showSpinner)
            failure(failure, ::handleFailure)
            observe(weatherResponse, ::setWeatherInfo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.getWeatherRandomPlace()
    }

    private fun setWeatherInfo(weatherView: WeatherView?) {
        weatherView?.let {
            binding.weatherIcon.setAnimation(
                resources.getString(
                    resources.getIdentifier(
                        weatherView.icon,
                        "string",
                        requireContext().packageName
                    )
                )
            )
            binding.weatherIcon.playAnimation()
            if (weatherView.description.isNotEmpty()) {
                binding.description.text = weatherView.description
            } else {
                binding.description.text = String.empty()
            }
            binding.temp.text = resources.getString(R.string.temp_degrees, weatherView.temp.toInt())
            binding.feelsLike.text = resources.getString(
                R.string.feels_like,
                weatherView.feelsLike.toInt()
            )
            if (weatherView.name.isNotEmpty() and weatherView.country.isNotEmpty()) {
                binding.location.text = resources.getString(
                    R.string.location,
                    weatherView.name,
                    Locale(String.empty(), weatherView.country).displayCountry
                )
            } else {
                binding.location.text = String.empty()
            }
            binding.latitude.text = resources.getString(R.string.latitude, weatherView.lat)
            binding.longitude.text = resources.getString(R.string.longitude, weatherView.lon)

            if (weatherView.isDay) {
                setColorDay()
            } else {
                setColorNight()
            }
            binding.refreshLocation.setOnClickListener { refreshLocation() }
            Log.i("place", weatherView.toString())
        }
    }

    private fun setColorDay() {
        binding.weatherBg.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_day)
        binding.description.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.temp.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.location.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.latitude.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.longitude.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.feelsLike.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
        binding.refreshLocation.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            )
        )
    }

    private fun setColorNight() {
        binding.weatherBg.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_night)
        binding.description.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.temp.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        binding.location.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.latitude.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.longitude.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.feelsLike.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        binding.refreshLocation.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
    }

    private fun refreshLocation() {
        weatherViewModel.getWeatherRandomPlace()
    }

    private fun handleFailure(failure: Throwable?) {
        if (failure?.message == ErrorHandler.NETWORK_ERROR_MESSAGE) {
        }
    }
}
