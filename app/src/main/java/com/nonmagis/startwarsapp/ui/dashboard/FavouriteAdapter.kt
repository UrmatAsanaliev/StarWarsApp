package com.nonmagis.startwarsapp.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonmagis.startwarsapp.databinding.ItemFavouriteBinding
import com.nonmagis.startwarsapp.databinding.ItemStarshipBinding
import com.nonmagis.startwarsapp.domain.favourite.model.FavouriteModel
import com.nonmagis.startwarsapp.domain.starship.model.Starship

class FavouriteAdapter(
    private val click: (FavouriteModel) -> Unit
): RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private val list = ArrayList<FavouriteModel>()

    fun setList(list: List<FavouriteModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class FavouriteViewHolder(private val binding: ItemFavouriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: FavouriteModel) {
            if (model.passengers == "") {
                binding.tvName.text = model.name
                binding.tvGenderText.text = "Gender:"
                binding.tvModel.text = model.gender
                binding.tvStarships.text = "Starships count:"
                binding.tvManufacturer.text = model.starships_count
                binding.tvPass.visibility = View.GONE
                binding.tvPassengers.visibility = View.GONE
            } else {
                binding.tvPass.visibility = View.VISIBLE
                binding.tvPassengers.visibility = View.VISIBLE
                binding.tvName.text = model.name
                binding.tvModel.text = model.model
                binding.tvManufacturer.text = model.manufacturer
                binding.tvPassengers.text = model.passengers
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder =
        FavouriteViewHolder(
            ItemFavouriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            click(list[position])
        }
    }
}