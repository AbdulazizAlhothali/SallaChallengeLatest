package com.example.sallachallenge.ui.main


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sallachallenge.models.brand.BrandData
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.models.items.Data
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: StoreRepo, private val json: DevelopersJson) : ViewModel()  {

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error
    private val _state = MutableLiveData<BrandData>()
    val state = _state
    val itemsState : LiveData<PagingData<Data>> = getItemData()





    private fun getItemData() = repo.getStoreData(json.id).cachedIn(viewModelScope).asLiveData()

    fun getBrandData() {
        viewModelScope.launch {
            when(val result = repo.getBrandData(json.id)){
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