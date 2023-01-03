package com.example.skycam2.presentation.units.data.network

import android.provider.ContactsContract.Data
import com.example.skycam2.core.BaseRetrofit
import com.example.skycam2.presentation.units.data.model.dto.DataCamera
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UnitsService {

    private val retrofit = BaseRetrofit.getRetrofit()

    suspend fun getUnits(units: String) : List<DataCamera>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UnitsApiClient::class.java).getCamerasApi(units)
            response.Data
        }
    }
}