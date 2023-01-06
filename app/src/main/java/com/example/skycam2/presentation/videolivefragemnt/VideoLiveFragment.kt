package com.example.skycam2.presentation.videolivefragemnt

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.skycam2.databinding.FragmentVideoBinding
import com.example.skycam2.presentation.units.ui.MainActivity
import com.example.skycam2.presentation.videolivefragemnt.viewmodel.VideoLiveVIewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCUtil
import org.videolan.libvlc.util.VLCVideoLayout
import java.lang.Math.E


class VideoLiveFragment : Fragment(), MediaPlayer.EventListener {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var libVlc: LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var videoLayout: VLCVideoLayout
    private lateinit var mVideoLiveVIewModel: VideoLiveVIewModel
    private lateinit var progressDialog: ProgressDialog
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
        val url = Uri.parse(mVideoLiveVIewModel.urlVideo)

        libVlc = LibVLC(mActivity)
        mediaPlayer = MediaPlayer(libVlc)
        videoLayout = binding.videoView
        mediaPlayer.attachViews(videoLayout, null, false, false)
        progressDialog = ProgressDialog(mActivity!!)


        binding.bnStartStop.setOnClickListener {
            playVideo(url)
        }

        binding.bntStop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.detachViews()
        }
    }

    private fun playVideo(url: Uri) {
        CoroutineScope(Dispatchers.Default).launch {
            val media = Media(libVlc, url)
            media.setHWDecoderEnabled(true, false)
            media.addOption(":network-caching=1500")
            mediaPlayer.media = media
            media.release()
            videoLayout.requestFocus()
            mediaPlayer.play()
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }


    override fun onStop()
    {
        super.onStop()
        mediaPlayer.stop()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onEvent(event: MediaPlayer.Event) {
        if (event.type == MediaPlayer.Event.Buffering){
            if(event.buffering == 100f){
                progressDialog.hide()
            }else{
                progressDialog.show()
            }
        }

    }
}