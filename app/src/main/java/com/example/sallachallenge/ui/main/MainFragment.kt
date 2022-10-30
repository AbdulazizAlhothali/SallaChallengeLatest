package com.example.sallachallenge.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sallachallenge.databinding.MainFragmentBinding
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.paging.StoreLoadStateAdapter
import com.example.sallachallenge.paging.StorePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.InputStream
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {


    private lateinit var binding: MainFragmentBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: StorePagingAdapter
    private lateinit var brandAdapter: BrandAdapter

    @Inject
    lateinit var devJson: DevelopersJson


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val headerState = StoreLoadStateAdapter {
            adapter.retry()
        }
        val footerState = StoreLoadStateAdapter {
            adapter.retry()
        }

        binding.dev = devJson
        adapter = StorePagingAdapter(devJson.font_family)
        val layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.setHasFixedSize(true)

        fetchData(devJson, headerState, footerState, layoutManager)



        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.progress.visibility = View.VISIBLE
                binding.btRetry.visibility = View.VISIBLE
                binding.rvMain.visibility = View.GONE
                binding.textView8.visibility = View.VISIBLE
                binding.textView8.text = it
                binding.btRetry.setOnClickListener {
                    fetchData(devJson, headerState, footerState, layoutManager)
                }
            }
        }
    }

    private fun fetchData(
        devJson: DevelopersJson,
        headerState: StoreLoadStateAdapter,
        footerState: StoreLoadStateAdapter,
        layoutManager: GridLayoutManager,
    ) {
        viewModel.getBrandData(devJson.id)
        viewModel.getItemData(devJson.id).observe(viewLifecycleOwner) {
            Log.e("MyStore", "here $it")
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.state.observe(viewLifecycleOwner) {
            if (it.success){
                binding.rvMain.visibility = View.VISIBLE
                binding.textView8.visibility = View.GONE
                binding.progress.visibility = View.GONE
                binding.btRetry.visibility = View.GONE
            }
            brandAdapter = BrandAdapter(listOf(it), devJson.font_family)
            val ca = ConcatAdapter()
            ca.addAdapter(brandAdapter)
            ca.addAdapter(
                adapter.withLoadStateHeaderAndFooter(
                    header = headerState,
                    footer = footerState
                )
            )
            binding.rvMain.adapter = ca
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == 0) {
                        2
                    } else if (position == 1 && headerState.itemCount > 0) {
                        2
                    } else if (position == ca.itemCount - 1 && footerState.itemCount > 0) {
                        2
                    } else {
                        1
                    }

                }
            }
        }

    }
}