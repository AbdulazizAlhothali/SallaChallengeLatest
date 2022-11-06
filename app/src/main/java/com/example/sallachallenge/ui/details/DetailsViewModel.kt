package com.example.sallachallenge.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sallachallenge.models.details.DetailsBase
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.ui.BaseViewModel
import com.example.sallachallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: StoreRepo)  : BaseViewModel() {


    private val _state = MutableLiveData<DetailsBase>()
    val state = _state

    fun getDetailsData(productId: String) {

        viewModelScope.launch {
            when(val result = repo.getDetailsData(productId)){
                is Resource.Success -> {
                    _state.postValue(result.data)
                    handleError(null)
                }
                is Resource.Error -> {
                    handleError(result.message)
                }
            }
        }

    }
}