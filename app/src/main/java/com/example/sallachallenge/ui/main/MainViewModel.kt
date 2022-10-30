package com.example.sallachallenge.ui.main


import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.sallachallenge.models.brand.BrandData
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StoreRepo) : ViewModel()  {

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    private val _state = MutableLiveData<BrandData>()
    val state = _state





    fun getItemData(header: String) = repo.getStoreData(header).cachedIn(viewModelScope).asLiveData()

    fun getBrandData(header: String) {


        viewModelScope.launch {
            when(val result = repo.getBrandData(header)){
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