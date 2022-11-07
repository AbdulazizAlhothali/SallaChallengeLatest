package com.example.sallachallenge.ui.details


import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.DetailsFragmentBinding
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
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
        this.detailViewModel = viewModel
        viewModel.getDetailsData(arg.productID)
        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                failedCaLL(this)
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { details ->
            if (details.success) {
                successCall(this)
            }
            if (details.data.promotionTitle != null) {
                promotion(this,details.data.promotionTitle)
            }
            adapter = DetailsAdapter(listOf(details.data.image))
            this.vpDetails.adapter = adapter
            TabLayoutMediator(this.tbIndicator, this.vpDetails) { _,_ ->
            }.attach()
        }
    }

    private fun successCall(detailsFragmentBinding: DetailsFragmentBinding) {
        detailsFragmentBinding.viewPagerCn.visibility = View.VISIBLE
        detailsFragmentBinding.cnContent.visibility = View.VISIBLE
        detailsFragmentBinding.progress.visibility = View.GONE
        detailsFragmentBinding.errorView.visibility = View.GONE
    }

    private fun failedCaLL(detailsFragmentBinding: DetailsFragmentBinding){
        detailsFragmentBinding.progress.visibility = View.VISIBLE
        detailsFragmentBinding.errorView.visibility = View.VISIBLE
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