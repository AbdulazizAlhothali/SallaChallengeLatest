package com.example.sallachallenge.paging


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.RecyclerViewItemBinding
import com.example.sallachallenge.models.items.Data
import com.example.sallachallenge.ui.main.MainFragmentDirections

class StorePagingAdapter(private val font: String) :
    PagingDataAdapter<Data, StorePagingAdapter.StoreViewHolder>(Comparator()) {


    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val bind = DataBindingUtil.inflate<RecyclerViewItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_item, parent, false
        )
        return StoreViewHolder(bind, font)
    }

    class StoreViewHolder(private val binding: RecyclerViewItemBinding, private val font: String) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Data) {

            binding.font = font
            binding.item = store
            if (store.promotion.title != null) {
                binding.ivPromo.visibility = View.VISIBLE
                binding.tvPromo.text = store.promotion.title
            } else {
                binding.ivPromo.visibility = View.GONE
            }
            binding.root.setOnClickListener {
                val action =
                    MainFragmentDirections.actionMainFragmentToDetailsFragment(store.id.toString())
                binding.root.findNavController().navigate(action)
            }


        }
    }

    class Comparator : DiffUtil.ItemCallback<Data>() {

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}