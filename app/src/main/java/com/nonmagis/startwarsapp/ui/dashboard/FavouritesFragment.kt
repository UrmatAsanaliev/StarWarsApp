package com.nonmagis.startwarsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nonmagis.startwarsapp.core.BaseFragment
import com.nonmagis.startwarsapp.core.UIState
import com.nonmagis.startwarsapp.databinding.FragmentFavouriteBinding
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {

    private val viewModel: FavouritesViewModel by viewModel()
    private val adapter: FavouriteAdapter by lazy { FavouriteAdapter(this::onClick)}

    override fun setupUI() {
        requireBinding().rvFavourites.adapter = adapter
        viewModel.getAllFavourites()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.data.collect {
                    when (it) {
                        is UIState.Error -> {

                        }
                        is UIState.Loading -> {

                        }
                        is UIState.Success -> {
                            adapter.setList(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun onClick(favouriteModel: FavouriteModel) {

    }
}