package com.example.sallachallenge.ui.main


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sallachallenge.models.brand.BrandData
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.models.items.Data
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.ui.BaseViewModel
import com.example.sallachallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StoreRepo) : BaseViewModel()  {


    private val _state = MutableLiveData<BrandData>()
    val state = _state
    val itemsState : LiveData<PagingData<Data>> = getItemData()





    private fun getItemData() = repo.getStoreData().cachedIn(viewModelScope).asLiveData()

    fun getBrandData() {
        viewModelScope.launch {
            when(val result = repo.getBrandData()){
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