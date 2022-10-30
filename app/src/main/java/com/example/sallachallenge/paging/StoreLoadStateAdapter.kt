package com.example.sallachallenge.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.ItemLoadingStateBinding

class StoreLoadStateAdapter(private val retry: ()-> Unit): LoadStateAdapter<StoreLoadStateAdapter.StoreStateViewHolder>() {
    override fun onBindViewHolder(
        holder: StoreStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): StoreStateViewHolder {
        val bind = DataBindingUtil.inflate<ItemLoadingStateBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_loading_state, parent, false
        )
        return StoreStateViewHolder(bind, retry)
    }

    class StoreStateViewHolder(private val binding: ItemLoadingStateBinding, private val retry: ()-> Unit): RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState){
            if (loadState is LoadState.Error) {
                binding.textViewError.text = loadState.error.localizedMessage
            }

            binding.progressbar.isVisible = loadState is LoadState.Loading
            binding.buttonRetry.isVisible = loadState is LoadState.Error
            binding.textViewError.isVisible = loadState is LoadState.Error
            binding.buttonRetry.setOnClickListener {
                retry()
            }

            binding.progressbar.visibility = View.VISIBLE
        }
    }
}