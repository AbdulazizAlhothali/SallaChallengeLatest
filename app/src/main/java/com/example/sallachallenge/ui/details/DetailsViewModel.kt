package com.example.sallachallenge.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sallachallenge.models.details.DetailsBase
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: StoreRepo)  : ViewModel() {

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    private val _state = MutableLiveData<DetailsBase>()
    val state = _state

    fun getDetailsData(header: String, productId: String) {

        viewModelScope.launch {
            when(val result = repo.getDetailsData(header, productId)){
                is Resource.Success -> {
                    _state.postValue(result.data)
                    _error.value = null
                }
                is Resource.Error -> {
                    _error.value = result.message
                }
            }
        }

    }
}