package com.fgdc.randomweather.utils.helpers

import android.content.Context
import com.fgdc.randomweather.utils.extensions.networkInfo
import javax.inject.Inject

class NetworkHandler
@Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}
