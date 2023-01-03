package com.example.skycam2.presentation.units.domain

import com.example.skycam2.presentation.units.data.UnitsRepository

class GetUnitsUseCase {

    private val repository = UnitsRepository()

    suspend operator fun invoke(units: String) = repository.getAllUnits(units)
}