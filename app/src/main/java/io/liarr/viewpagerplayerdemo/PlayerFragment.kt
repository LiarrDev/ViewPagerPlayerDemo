package io.liarr.viewpagerplayerdemo

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.FrameLayout
import androidx.lifecycle.lifecycleScope
import io.liarr.viewpagerplayerdemo.databinding.FragmentPlayerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PlayerFragment(private val url: String) : Fragment() {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private val mediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer.apply {
            setOnPreparedListener {
                binding.videoProgressBar.max = mediaPlayer.duration
                seekTo(1)
                binding.loadingProgressBar.visibility = View.INVISIBLE
                setOnVideoSizeChangedListener { _, width, height -> resizePlayer(width, height) }
            }
            setDataSource(url)
            prepareAsync()
            binding.loadingProgressBar.visibility = View.VISIBLE
        }
        lifecycleScope.launch {
            while (true) {
                binding.videoProgressBar.progress = mediaPlayer.currentPosition
                delay(500)
            }
        }
        binding.surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {}

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                mediaPlayer.setDisplay(holder)
                mediaPlayer.setScreenOnWhilePlaying(true)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })
    }

    private fun resizePlayer(width: Int, height: Int) {
        if (width == 0 || height == 0) {
            return
        }
        binding.surfaceView.layoutParams = if (width > height) {
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                binding.playerFrame.width * height / width,
                Gravity.CENTER
            )
        } else {
            FrameLayout.LayoutParams(
                binding.playerFrame.height * width / height,
                FrameLayout.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
        lifecycleScope.launch {
            while (mediaPlayer.isPlaying.not()) {
                mediaPlayer.start()
                delay(500)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}