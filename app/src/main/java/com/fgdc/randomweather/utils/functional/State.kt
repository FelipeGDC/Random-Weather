package com.fgdc.randomweather.utils.functional

import com.fgdc.randomweather.utils.exception.ErrorHandler

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()

class Error(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : State<Nothing>()

class ErrorNoConnection(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.NETWORK_ERROR_MESSAGE
) : State<Nothing>()

class BadRequest(
    val exception: Throwable,
    val message: String = exception.message ?: ErrorHandler.UNKNOWN_ERROR
) : State<Nothing>()
