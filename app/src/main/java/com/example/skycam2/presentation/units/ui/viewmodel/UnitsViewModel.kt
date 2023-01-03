package com.example.skycam2.presentation.units.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skycam2.presentation.units.data.model.dto.DataCamera
import com.example.skycam2.presentation.units.domain.GetUnitsUseCase
import kotlinx.coroutines.launch

class UnitsViewModel : ViewModel() {
    val unitsModel = MutableLiveData<List<DataCamera?>>()

    var getUnitsUseCase = GetUnitsUseCase()

    fun onCreate(units: String){
        viewModelScope.launch {
            val result = getUnitsUseCase(units)

            unitsModel.postValue(result)
        }
    }
}