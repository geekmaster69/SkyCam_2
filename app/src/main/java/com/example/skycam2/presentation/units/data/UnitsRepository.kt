package com.example.skycam2.presentation.units.data

import com.example.skycam2.presentation.units.data.model.UnitsProvider
import com.example.skycam2.presentation.units.data.model.dto.DataCamera
import com.example.skycam2.presentation.units.data.network.UnitsService

class UnitsRepository {

    private val api = UnitsService()

    suspend fun getAllUnits(units: String) : List<DataCamera>{
        val response = api.getUnits(units)
        UnitsProvider.dataCamera = response
        return response
    }
}