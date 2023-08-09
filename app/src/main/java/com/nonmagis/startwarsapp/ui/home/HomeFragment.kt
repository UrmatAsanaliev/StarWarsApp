package com.nonmagis.startwarsapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nonmagis.startwarsapp.R
import com.nonmagis.startwarsapp.core.BaseFragment
import com.nonmagis.startwarsapp.core.PromptUtils
import com.nonmagis.startwarsapp.core.PromptUtils.positiveButton
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.databinding.FragmentHomeBinding
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModel()

    private val adapter: HomeAdapter by lazy { HomeAdapter(this::onClick) }
    override fun setupUI() {
        requireBinding().rvHome.adapter = adapter
        viewModel.getPerson("")
        getPerson()
        setFavourite()
    }

    private fun setFavourite() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.result.collect {
                    when (it) {
                        is UIState.Error -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvHome.isGone = false
                        }
                        is UIState.Loading -> {
                            requireBinding().progress.isGone = false
                            requireBinding().rvHome.isGone = true

                        }
                        is UIState.Success -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvHome.isGone = false
                        }
                    }
                }
            }
        }
    }

    override fun setupObservers() {
        requireBinding().etSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = newText?.toList()?.size!!
                if (list >= 2 || list == 0) {
                    viewModel.getPerson(newText)
                }
                return true
            }
        })
    }

    private fun getPerson() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.data.collect {
                    when (it) {
                        is UIState.Error -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvHome.isGone = false
                        }
                        is UIState.Loading -> {
                            requireBinding().progress.isGone = false
                            requireBinding().rvHome.isGone = true

                        }
                        is UIState.Success -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvHome.isGone = false
                            adapter.setList(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun onClick(model: ResultModel) {
        PromptUtils.alertDialog(requireContext(), dialogBuilder = {
            positiveButton("Save", handleClick = {
                val data = FavouriteModel(
                    name = model.name,
                    gender = model.gender,
                    starships_count = model.starships.size.toString()
                )
                viewModel.setFavourites(data)
            })
        })
    }
}