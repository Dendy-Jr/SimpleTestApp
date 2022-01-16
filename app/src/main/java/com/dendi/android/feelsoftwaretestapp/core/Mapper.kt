package com.dendi.android.feelsoftwaretestapp.core

interface Mapper<S, R> {
    fun map(item: S): R
}