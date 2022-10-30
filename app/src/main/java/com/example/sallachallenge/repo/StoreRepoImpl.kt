package com.example.sallachallenge.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sallachallenge.paging.StorePagingSource
import com.example.sallachallenge.retrofit.StoreApi
import com.example.sallachallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreRepoImpl @Inject constructor(private val storeApi: StoreApi): StoreRepo {
    override fun getStoreData(header: String) = Pager(
        config = PagingConfig(pageSize = 5, initialLoadSize = 5, maxSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {StorePagingSource(storeApi, header)}
    ).flow

    override suspend fun getBrandData(header: String) = withContext(Dispatchers.IO){
        return@withContext try {
            Resource.Success(storeApi.getBrandeData(header))
        } catch (e: Exception){
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getDetailsData(header: String, productId: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            Resource.Success(storeApi.getDetailsData(header, productId))
        } catch (e: Exception){
            Resource.Error(e.message ?: "An unknown error occurred")
        }

    }

}