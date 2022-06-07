package com.daveloper.gothouses.data.remote

import com.daveloper.gothouses.data.remote.dto.HouseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GoTService {

    @GET("api/houses")
    suspend fun getHouses(): List<HouseDto>

    @GET("api/houses/{id}")
    suspend fun getHouseById(@Path("id") id: Int): HouseDto
}