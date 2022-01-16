package com.dendi.android.feelsoftwaretestapp.data.cars

import com.google.gson.annotations.SerializedName

data class CarCloud(
    @SerializedName(value = "date")
    val date: String,
    @SerializedName(value = "number")
    val number: Int,
    @SerializedName(value = "state")
    val state: String
)