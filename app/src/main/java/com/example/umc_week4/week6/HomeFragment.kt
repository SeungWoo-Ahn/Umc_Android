package com.example.umc_week4.week6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umc_week4.R
import com.example.umc_week4.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val tabTitleArray = arrayOf(
        "전체보기",
        "인기순",
        "최신순"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewPager = binding.homeVp
        val tabLayout = binding.homeTab

        viewPager.adapter = ViewPagerAdapter(parentFragmentManager,lifecycle)

        TabLayoutMediator(tabLayout, viewPager) {tab,position ->
            tab.text = tabTitleArray[position]
        }.attach()


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}