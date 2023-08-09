package com.nonmagis.startwarsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nonmagis.startwarsapp.domain.people.model.ResultModel
import com.nonmagis.startwarsapp.databinding.ItemHomeBinding

class HomeAdapter(
    private val click: (ResultModel) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val list = ArrayList<ResultModel>()

    fun setList(list: List<ResultModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class HomeViewHolder(private val binding: ItemHomeBinding): ViewHolder(binding.root) {
        fun onBind(model: ResultModel) {
            binding.tvName.text = model.name
            binding.tvGender.text = model.gender
            binding.tvCount.text = model.starships.size.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.onBind(list[position])

        holder.itemView.setOnClickListener {
            click(list[position])
        }
    }
}