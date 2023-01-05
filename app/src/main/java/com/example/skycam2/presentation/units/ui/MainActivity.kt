package com.example.skycam2.presentation.units.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skycam2.R
import com.example.skycam2.core.Constans
import com.example.skycam2.databinding.ActivityMainBinding
import com.example.skycam2.presentation.units.adapter.OnClickListener
import com.example.skycam2.presentation.units.adapter.UnitsAdapter
import com.example.skycam2.presentation.units.data.model.dto.Channel
import com.example.skycam2.presentation.units.data.model.dto.DataCamera
import com.example.skycam2.presentation.units.ui.viewmodel.UnitsViewModel
import com.example.skycam2.presentation.videolivefragemnt.VideoLiveFragment
import com.example.skycam2.presentation.videolivefragemnt.viewmodel.VideoLiveVIewModel

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mUnitsAdapter: UnitsAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    private lateinit var mVideoLiveVIewModel: VideoLiveVIewModel
    private val unitsViewModel: UnitsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViewModel()
        setupRecyclerView()

    }

    private fun setupViewModel() {
        mVideoLiveVIewModel = ViewModelProvider(this)[VideoLiveVIewModel::class.java]
    }

    private fun setupRecyclerView() {

        unitsViewModel.onCreate(Constans.UNITS_TEST)

        unitsViewModel.unitsModel.observe(this) { dataCamera ->
            mUnitsAdapter = UnitsAdapter(dataCamera as List<DataCamera>, this)
            mLinearLayoutManager = LinearLayoutManager(this)

            binding.rvUnits.apply {
                layoutManager = mLinearLayoutManager
                adapter = mUnitsAdapter
            }
        }
    }

    override fun onClickListener(channel: Channel) {
        launchVideoFragment(channel)
        Toast.makeText(this, channel.idChannel, Toast.LENGTH_SHORT).show()
    }

    private fun launchVideoFragment(channel: Channel) {

        mVideoLiveVIewModel.setChannelSelected(channel)
        val fragment = VideoLiveFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()


        fragmentTransaction.add(R.id.containerMain, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}