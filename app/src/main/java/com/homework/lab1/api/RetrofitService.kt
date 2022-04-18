package com.homework.lab1.api;

import com.homework.lab1.ui.main.Balance;
import com.homework.lab1.ui.main.Tariff
import com.homework.lab1.ui.main.User
import retrofit2.Call
import retrofit2.http.GET;

interface RetrofitServices {
    @GET("/balance")
    suspend fun getBalanceList(): List<Balance>

    @GET("/tariffs")
    suspend fun getTariffsList(): List<Tariff>

    @GET("/userInfo")
    suspend fun getUserList(): List<User>
}