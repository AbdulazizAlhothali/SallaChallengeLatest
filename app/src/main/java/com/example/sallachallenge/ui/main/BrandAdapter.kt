package com.example.sallachallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.StaticItemBinding
import com.example.sallachallenge.models.brand.BrandData

class BrandAdapter (/*private val brand: List<BrandData>, */private val font: String): ListAdapter<BrandData, BrandAdapter.BrandViewHolder>(BrandComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val bind = DataBindingUtil.inflate<StaticItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.static_item, parent, false
        )
        return BrandViewHolder(bind, font)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val storeBrand = getItem(position)
        holder.bind(storeBrand)
    }





    class BrandViewHolder(private val binding: StaticItemBinding, private val font: String): RecyclerView.ViewHolder(binding.root){
        fun bind(brandData: BrandData){
            binding.brand = brandData
            binding.font = font

        }
    }

    class BrandComparator : DiffUtil.ItemCallback<BrandData>() {

        override fun areItemsTheSame(oldItem: BrandData, newItem: BrandData): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: BrandData, newItem: BrandData): Any? {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BrandData, newItem: BrandData): Boolean {
            return oldItem == newItem
        }
    }
}