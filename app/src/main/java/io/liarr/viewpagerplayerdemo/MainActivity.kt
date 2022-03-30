package io.liarr.viewpagerplayerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import io.liarr.viewpagerplayerdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainViewPager.apply {
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount() = 3

                override fun createFragment(position: Int) = when (position) {
                    0 -> LiveFragment()
                    1 -> VideoFragment()
                    else -> MeFragment()
                }
            }
            setCurrentItem(1, false)
        }

        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, i ->
            tab.text = when (i) {
                0 -> getString(R.string.live_title)
                1 -> getString(R.string.video_title)
                else -> getString(R.string.me_title)
            }
        }.attach()
    }
}