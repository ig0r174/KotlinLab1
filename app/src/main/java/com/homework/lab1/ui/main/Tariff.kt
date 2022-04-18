package com.homework.lab1.ui.main

import com.google.gson.annotations.SerializedName

data class Tariff(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("cost")
    val amount: Double
)
