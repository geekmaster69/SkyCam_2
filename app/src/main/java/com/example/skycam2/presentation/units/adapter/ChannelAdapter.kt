package com.example.skycam2.presentation.units.adapter

import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skycam2.R
import com.example.skycam2.databinding.ItemChannelBinding
import com.example.skycam2.presentation.units.data.model.dto.Channel


class ChannelAdapter(private val channels: List<Channel>, private val listener: OnClickListener):
RecyclerView.Adapter<ChannelAdapter.ViewHolder>(){
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val channel = channels[position]
        holder.setListener(channel)

        with(holder){
            binding.tvIdChannel.text = channel.idChannel
            binding.tvNameChannel.text = channel.nameChannel
            binding.tvLinkChannel.text = channel.linkChannel
        }
    }

    override fun getItemCount(): Int = channels.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemChannelBinding.bind(view)

        fun setListener(channel: Channel){
            binding.root.setOnClickListener {
                listener.onClickListener(channel)
            }
        }

    }
}