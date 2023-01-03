package com.example.skycam2.presentation.units.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skycam2.R
import com.example.skycam2.core.Constans
import com.example.skycam2.databinding.ItemUnitsBinding
import com.example.skycam2.presentation.units.data.model.dto.Channel
import com.example.skycam2.presentation.units.data.model.dto.DataCamera

class UnitsAdapter(private val units: List<DataCamera>):
RecyclerView.Adapter<UnitsAdapter.ViewHolder>(), OnClickListener{
    private lateinit var context: Context
    private lateinit var mChannelAdapter: ChannelAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_units, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val unit = units[position]

        with(holder){
            binding.tvCompany.text = unit.company
            binding.tvSubCompany.text = unit.subCompany
            binding.tvPlate.text = unit.plate
            binding.tvIdCamera.text = unit.idCamera
            binding.tvNameCamera.text = unit.nameCamera

            if (unit.online == "1") binding.tvOnline.text = Constans.ON_LINE
            else binding.tvOnline.text = Constans.OF_LINE

            mChannelAdapter = ChannelAdapter(unit.channels, this@UnitsAdapter)
            mLinearLayoutManager = LinearLayoutManager(context)

            binding.rvChannels.apply {
                layoutManager = mLinearLayoutManager
                adapter = mChannelAdapter
            }

            binding.rvChannels.visibility = View.GONE

            binding.root.setOnClickListener {
                if (unit.isVisible) binding.rvChannels.visibility = View.GONE
                else binding.rvChannels.visibility = View.VISIBLE
                unit.isVisible = !unit.isVisible
            }

        }

    }

    override fun getItemCount(): Int = units.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUnitsBinding.bind(view)


    }

    override fun onClickListener(channel: Channel) {
        Toast.makeText(context, channel.linkChannel, Toast.LENGTH_SHORT).show()

    }

}