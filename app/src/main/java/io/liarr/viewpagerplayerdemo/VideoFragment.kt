package io.liarr.viewpagerplayerdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.liarr.viewpagerplayerdemo.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.videoViewPager.apply {
            adapter = object : FragmentStateAdapter(this@VideoFragment) {
                override fun getItemCount() = videoUrls.size
                override fun createFragment(position: Int) = PlayerFragment(videoUrls[position])
            }
            offscreenPageLimit = 5
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}