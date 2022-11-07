package com.example.sallachallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewDataBinding, K: BaseViewModel>(@LayoutRes private val layoutResId : Int): Fragment() {

    private var _binding: T? = null
    protected val binding : T get() = _binding!!
    protected lateinit var viewModel: K
    protected abstract fun getViewModelClass(): Class<K>
    protected open fun T.initialize(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}