package com.example.umc_week4.week6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.umc_week4.R
import com.example.umc_week4.databinding.FragmentUserBinding
import me.relex.circleindicator.CircleIndicator
import me.relex.circleindicator.CircleIndicator3

class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewPager : ViewPager2 = binding.userVp
        val indicator : CircleIndicator3 = binding.indicator

        viewPager.adapter = UserAdapter(parentFragmentManager, lifecycle)
        indicator.setViewPager(viewPager)
        indicator.createIndicators(3,0)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}