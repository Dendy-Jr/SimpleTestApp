package com.dendi.android.feelsoftwaretestapp.core

import com.dendi.android.feelsoftwaretestapp.R
import retrofit2.HttpException
import java.lang.IllegalArgumentException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ExceptionMapper : Mapper<Exception, String> {

    class ExceptionMapperImpl(private val resourceProvider: ResourceProvider) : ExceptionMapper {

        override fun map(item: Exception): String =
            when (item) {
                is UnknownHostException -> resourceProvider.provideString(R.string.no_connection_message)
                is HttpException -> resourceProvider.provideString(R.string.not_correctly_entered_word_error)
                is SocketTimeoutException -> resourceProvider.provideString(R.string.timeout_error)
                is NullPointerException -> resourceProvider.provideString(R.string.null_error)
                else -> throw IllegalArgumentException("data -> ExceptionMapper not found ${item.message}")
            }
    }
}