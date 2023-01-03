package com.example.skycam2.presentation.units.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skycam2.core.Constans
import com.example.skycam2.databinding.ActivityMainBinding
import com.example.skycam2.presentation.units.adapter.UnitsAdapter
import com.example.skycam2.presentation.units.data.model.dto.DataCamera
import com.example.skycam2.presentation.units.ui.viewmodel.UnitsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUnitsAdapter: UnitsAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private val unitsViewModel: UnitsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()


    }

    private fun setupRecyclerView() {

        unitsViewModel.onCreate(Constans.UNITS_TEST)

        unitsViewModel.unitsModel.observe(this) { dataCamera ->
            mUnitsAdapter = UnitsAdapter(dataCamera as List<DataCamera>)
            mLinearLayoutManager = LinearLayoutManager(this)

            binding.rvUnits.apply {
                layoutManager = mLinearLayoutManager
                adapter = mUnitsAdapter
            }
        }
    }

}