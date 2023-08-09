package com.nonmagis.startwarsapp.ui.pager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nonmagis.startwarsapp.ui.home.HomeFragment
import com.nonmagis.startwarsapp.ui.starship.StarshipFragment

class PagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> StarshipFragment()
            else -> HomeFragment()
        }
    }
}