package com.example.skycam2.presentation.videolivefragemnt

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.skycam2.databinding.FragmentVideoBinding
import com.example.skycam2.presentation.units.ui.MainActivity
import com.example.skycam2.presentation.videolivefragemnt.viewmodel.VideoLiveVIewModel
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCUtil
import org.videolan.libvlc.util.VLCVideoLayout


class VideoLiveFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var libVlc: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var videoLayout: VLCVideoLayout
    private lateinit var mVideoLiveVIewModel: VideoLiveVIewModel
    private var mActivity: MainActivity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mVideoLiveVIewModel = ViewModelProvider(requireActivity())[VideoLiveVIewModel::class.java]

        mActivity = activity as? MainActivity

        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)




        libVlc = LibVLC(mActivity)
        mediaPlayer = MediaPlayer(libVlc)
        videoLayout = binding.videoView
        mediaPlayer.attachViews(videoLayout, null, false, false)

        binding.bnStartStop.setOnClickListener {

            starVideo()
        }

        binding.bntStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.detachViews()
        }


    }

    private fun starVideo() {
        try {
            val uri2 = Uri.parse(mVideoLiveVIewModel.urlVideo)
            val media = Media(libVlc, uri2)
            media.setHWDecoderEnabled(true, false)
            media.addOption(":network-caching=600")
            mediaPlayer.media = media
            media.release()
            mediaPlayer.play()
        } catch (e: Exception) {
            starVideo()
        }


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

}