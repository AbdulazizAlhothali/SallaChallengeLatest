package com.example.sallachallenge.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.ImageSliderItemBinding
import com.example.sallachallenge.models.details.Images

class DetailsAdapter(private val images: List<Images>): RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val bind = DataBindingUtil.inflate<ImageSliderItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.image_slider_item, parent, false
        )
        return DetailsViewHolder(bind)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }



    class DetailsViewHolder(private val binding: ImageSliderItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(image: Images){
            binding.image = image
        }
    }
}
