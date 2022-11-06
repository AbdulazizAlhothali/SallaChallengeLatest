package com.example.sallachallenge.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.DetailsFragmentBinding
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding, DetailsViewModel>(R.layout.details_fragment) {

    private val arg: DetailsFragmentArgs by navArgs()
    private lateinit var adapter: DetailsAdapter

    @Inject
    lateinit var devJson: DevelopersJson


    override fun DetailsFragmentBinding.initialize(){
        this.dev = devJson
        viewModel.getDetailsData(arg.productID)
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                failedCaLL(this, errorMessage = it)
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { details ->
            binding.details = details
            if (details.success) {
                successCall(this)
            }
            if (details.data.promotionTitle != null) {
                promotion(this,details.data.promotionTitle)
            }
            adapter = DetailsAdapter(listOf(details.data.image))
            //Log.e("details", "${details.data.images.size}")
            binding.vpDetails.adapter = adapter
            binding.indicator.setViewPager(binding.vpDetails)
        }
    }

    private fun successCall(detailsFragmentBinding: DetailsFragmentBinding) {
        detailsFragmentBinding.floatingActionButton3.visibility = View.VISIBLE
        detailsFragmentBinding.floatingActionButton4.visibility = View.VISIBLE
        detailsFragmentBinding.indicator.visibility = View.VISIBLE
        detailsFragmentBinding.vpDetails.visibility = View.VISIBLE
        detailsFragmentBinding.cnContent.visibility = View.VISIBLE
        detailsFragmentBinding.progress.visibility = View.GONE
        detailsFragmentBinding.btRetry.visibility = View.GONE
        detailsFragmentBinding.textView8.visibility = View.GONE
    }

    private fun failedCaLL(detailsFragmentBinding: DetailsFragmentBinding, errorMessage: String){
        detailsFragmentBinding.progress.visibility = View.VISIBLE
        detailsFragmentBinding.btRetry.visibility = View.VISIBLE
        detailsFragmentBinding.textView8.visibility = View.VISIBLE
        detailsFragmentBinding.textView8.text = errorMessage
        detailsFragmentBinding.btRetry.setOnClickListener {
            viewModel.getDetailsData(arg.productID)
        }
    }

    private fun promotion(detailsFragmentBinding: DetailsFragmentBinding,promo: String){
        detailsFragmentBinding.ivPromo.visibility = View.VISIBLE
        detailsFragmentBinding.tvPromo.text = promo
    }

    override fun getViewModelClass() = DetailsViewModel::class.java
}