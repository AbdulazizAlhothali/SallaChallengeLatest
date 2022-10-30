package com.example.sallachallenge.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sallachallenge.databinding.DetailsFragmentBinding
import com.example.sallachallenge.models.developersjson.DevelopersJson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val arg: DetailsFragmentArgs by navArgs()
    private lateinit var binding: DetailsFragmentBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var adapter: DetailsAdapter

    @Inject
    lateinit var devJson: DevelopersJson


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        devJson
        binding.dev = devJson
        viewModel.getDetailsData(devJson.id, arg.productID)
        viewModel.error.observe(viewLifecycleOwner) {
            if(it != null) {
                binding.progress.visibility = View.VISIBLE
                binding.btRetry.visibility = View.VISIBLE
                binding.textView8.visibility = View.VISIBLE
                binding.textView8.text = it
                binding.btRetry.setOnClickListener {
                    viewModel.getDetailsData(devJson.id, arg.productID)
                }
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { details ->
            binding.details = details
            if (details.success){
                binding.floatingActionButton3.visibility = View.VISIBLE
                binding.floatingActionButton4.visibility = View.VISIBLE
                binding.indicator.visibility = View.VISIBLE
                binding.vpDetails.visibility = View.VISIBLE
                binding.cnContent.visibility = View.VISIBLE
                binding.progress.visibility = View.GONE
                binding.btRetry.visibility = View.GONE
                binding.textView8.visibility = View.GONE
            }
            if (details.data.promotion.title != null) {
                binding.ivPromo.visibility = View.VISIBLE
                binding.tvPromo.text = details.data.promotion.title
            }
            adapter = DetailsAdapter(details.data.images)
            Log.e("details", "${details.data.images.size}")
            binding.vpDetails.adapter = adapter
            binding.indicator.setViewPager(binding.vpDetails)
        }


    }

}