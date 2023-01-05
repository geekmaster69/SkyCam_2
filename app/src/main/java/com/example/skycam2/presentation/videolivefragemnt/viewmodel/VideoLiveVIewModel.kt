package com.example.skycam2.presentation.videolivefragemnt.viewmodel

import androidx.lifecycle.ViewModel
import com.example.skycam2.presentation.units.data.model.dto.Channel

class VideoLiveVIewModel : ViewModel() {

     var urlVideo: String = ""


    fun setChannelSelected(channel: Channel){
        urlVideo = channel.linkChannel
    }






}