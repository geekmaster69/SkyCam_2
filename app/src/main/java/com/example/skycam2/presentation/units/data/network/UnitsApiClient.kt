package com.example.skycam2.presentation.units.data.network

import com.example.skycam2.core.Constans
import com.example.skycam2.presentation.units.data.model.dto.UnitsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UnitsApiClient {

    @FormUrlEncoded
    @POST(Constans.CAMERAS_PATH)
    suspend fun getCamerasApi(@Field("units") units: String
    ): UnitsResponse

}