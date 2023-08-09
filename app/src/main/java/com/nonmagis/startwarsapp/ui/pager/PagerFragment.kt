package com.nonmagis.startwarsapp.ui.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.nonmagis.startwarsapp.R
import com.nonmagis.startwarsapp.core.BaseFragment
import com.nonmagis.startwarsapp.databinding.FragmentPagerBinding


class PagerFragment : BaseFragment<FragmentPagerBinding>(FragmentPagerBinding::inflate) {

    override fun setupUI() {
        requireBinding().pager.adapter = PagerAdapter(this)
        val fragmentsName = listOf(
            "People",
            "Starships"
        )
        TabLayoutMediator(requireBinding().tabLayout, requireBinding().pager) { tab, position ->
            tab.text = fragmentsName[position]
        }.attach()
    }
}