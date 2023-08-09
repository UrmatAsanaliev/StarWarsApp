package com.nonmagis.startwarsapp.ui.starship

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nonmagis.startwarsapp.R
import com.nonmagis.startwarsapp.core.BaseFragment
import com.nonmagis.startwarsapp.core.PromptUtils
import com.nonmagis.startwarsapp.core.PromptUtils.negativeButton
import com.nonmagis.startwarsapp.core.PromptUtils.positiveButton
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.databinding.FragmentStarshipBinding
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.starship.model.Starship
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class StarshipFragment : BaseFragment<FragmentStarshipBinding>(FragmentStarshipBinding::inflate) {

    private val viewModel: StarshipViewModel by viewModel()
    private val adapter: StarshipAdapter by lazy {
        StarshipAdapter(this::onClick)
    }
    override fun setupUI() {
        requireBinding().rvFavourites.adapter = adapter
        viewModel.getStarship("")
        getStarships()
        setStarships()
    }

    private fun setStarships() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.result.collect {
                    when (it) {
                        is UIState.Error -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvFavourites.isGone = false
                        }
                        is UIState.Loading -> {
                            requireBinding().progress.isGone = false
                            requireBinding().rvFavourites.isGone = true
                        }
                        is UIState.Success -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvFavourites.isGone = false
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
                    viewModel.getStarship(newText)
                }
                return true
            }
        })
    }

    private fun getStarships() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.data.collect {
                    when (it) {
                        is UIState.Error -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvFavourites.isGone = false
                        }
                        is UIState.Loading -> {
                            requireBinding().progress.isGone = false
                            requireBinding().rvFavourites.isGone = true
                        }
                        is UIState.Success -> {
                            requireBinding().progress.isGone = true
                            requireBinding().rvFavourites.isGone = false
                        }
                    }
                }
            }
        }
    }

    private fun onClick(model: Starship) {
        PromptUtils.alertDialog(requireContext(), dialogBuilder = {
            positiveButton("Save", handleClick = {
                val data = FavouriteModel(
                    name = model.name,
                    passengers = model.passengers,
                    model = model.model,
                    manufacturer = model.manufacturer
                )
                viewModel.setFavourites(data)
            })
        })
    }
}