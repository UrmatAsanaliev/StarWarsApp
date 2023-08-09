package com.nonmagis.startwarsapp.ui.starship

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nonmagis.startwarsapp.databinding.ItemStarshipBinding
import com.nonmagis.startwarsapp.domain.starship.model.Starship

class StarshipAdapter(
    private val click: (Starship) -> Unit
): RecyclerView.Adapter<StarshipAdapter.StarshipViewHolder>() {

    private val list = ArrayList<Starship>()

    fun setList(list: List<Starship>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class StarshipViewHolder(private val binding: ItemStarshipBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: Starship) {
            binding.tvName.text = model.name
            binding.tvModel.text = model.model
            binding.tvManufacturer.text = model.manufacturer
            binding.tvPassengers.text = model.passengers
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder =
        StarshipViewHolder(
            ItemStarshipBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            click(list[position])
        }
    }
}