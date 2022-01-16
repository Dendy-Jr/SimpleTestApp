package com.dendi.android.feelsoftwaretestapp.presentation.util

import com.dendi.android.feelsoftwaretestapp.R

enum class CarFilter(val state: String, val color: Int) {
    AVAILABLE("available", R.color.circle_green),
    HIDDEN("hidden", R.color.circle_blue),
    DISABLED("disabled", R.color.circle_yellow);
}
