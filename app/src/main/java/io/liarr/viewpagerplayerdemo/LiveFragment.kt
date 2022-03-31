package io.liarr.viewpagerplayerdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.liarr.viewpagerplayerdemo.databinding.FragmentLiveBinding

class LiveFragment : Fragment() {

    private var _binding: FragmentLiveBinding? = null
    private val binding get() = _binding!!

    private val list = listOf(
        Live(R.drawable.live_cover_0, (1000..10000).random()),
        Live(R.drawable.live_cover_1, (1000..10000).random()),
        Live(R.drawable.live_cover_2, (1000..10000).random()),
        Live(R.drawable.live_cover_3, (1000..10000).random()),
        Live(R.drawable.live_cover_4, (1000..10000).random()),
        Live(R.drawable.live_cover_5, (1000..10000).random()),
        Live(R.drawable.live_cover_6, (1000..10000).random()),
        Live(R.drawable.live_cover_7, (1000..10000).random()),
        Live(R.drawable.live_cover_8, (1000..10000).random()),
        Live(R.drawable.live_cover_9, (1000..10000).random()),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val liveAdapter = LiveAdapter(list)
        binding.liveRecycler.apply {
            adapter = liveAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}