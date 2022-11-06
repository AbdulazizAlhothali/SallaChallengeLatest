package com.example.sallachallenge.ui.main

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sallachallenge.R
import com.example.sallachallenge.databinding.MainFragmentBinding
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.paging.StoreLoadStateAdapter
import com.example.sallachallenge.paging.StorePagingAdapter
import com.example.sallachallenge.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding,MainViewModel>(R.layout.main_fragment) {



    private lateinit var adapter: StorePagingAdapter
    private lateinit var brandAdapter: BrandAdapter
    private lateinit var concatAdapter: ConcatAdapter
    lateinit var headerState: StoreLoadStateAdapter
    lateinit var footerState: StoreLoadStateAdapter

    @Inject
    lateinit var devJson: DevelopersJson


    override fun MainFragmentBinding.initialize(){
        this.dev = devJson
        setRecyclerView(this)
        setObservers(this)
    }

    private fun setRecyclerView(mainFragmentBinding: MainFragmentBinding) {

        adapter = StorePagingAdapter(devJson.font_family)
        brandAdapter = BrandAdapter(devJson.font_family)
        concatAdapter = ConcatAdapter()
        headerState = StoreLoadStateAdapter {
            adapter.retry()
        }
        footerState = StoreLoadStateAdapter {
            adapter.retry()
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)
        mainFragmentBinding.rvMain.layoutManager = layoutManager
        mainFragmentBinding.rvMain.setHasFixedSize(true)


        concatAdapter.addAdapter(brandAdapter)
        concatAdapter.addAdapter(
            adapter.withLoadStateHeaderAndFooter(
                header = headerState,
                footer = footerState
            )
        )

        mainFragmentBinding.rvMain.adapter = concatAdapter
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    2
                } else if (position == 1 && headerState.itemCount > 0) {
                    2
                } else if (position == concatAdapter.itemCount - 1 && footerState.itemCount > 0) {
                    2
                } else {
                    1
                }
            }
        }
    }

    private fun setObservers(mainFragmentBinding: MainFragmentBinding) {
        viewModel.getBrandData()
        viewModel.itemsState.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.state.observe(viewLifecycleOwner) {

            if (it.success) {
                mainFragmentBinding.rvMain.visibility = View.VISIBLE
                mainFragmentBinding.errorView.visibility = View.GONE
                mainFragmentBinding.progress.visibility = View.GONE

                brandAdapter.submitList(listOf(it))
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it != null) {
                mainFragmentBinding.progress.visibility = View.VISIBLE
                mainFragmentBinding.errorView.visibility = View.VISIBLE
                mainFragmentBinding.rvMain.visibility = View.GONE
                mainFragmentBinding.textView8.text = it
                mainFragmentBinding.btRetry.setOnClickListener {
                    setObservers(mainFragmentBinding)
                }
            }
        }
    }

    override fun getViewModelClass() = MainViewModel::class.java
}