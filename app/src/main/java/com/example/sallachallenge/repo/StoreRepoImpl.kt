package com.example.sallachallenge.repo

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.sallachallenge.models.ExampleJson2KtKotlin
import com.example.sallachallenge.paging.StorePagingSource
import com.example.sallachallenge.retrofit.StoreApi
import com.example.sallachallenge.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class StoreRepoImpl @Inject constructor(private val storeApi: StoreApi): StoreRepo {
    override fun getStoreData() = Pager(
        config = PagingConfig(pageSize = 5, initialLoadSize = 5, maxSize = 20, enablePlaceholders = false),
        pagingSourceFactory = {StorePagingSource(storeApi)}
    ).flow

    override suspend fun getBrandData() = withContext(Dispatchers.IO){
        return@withContext try {
            Resource.Success(storeApi.getBrandeData())
        } catch (e: HttpException){
            val error = e.response()?.errorBody()?.string()
            val errorMessage = Gson().fromJson(error, ExampleJson2KtKotlin::class.java)
            Resource.Error(errorMessage.error?.message ?: e.message ?: "An unknown error occurred")
        } catch (e: Exception){
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun getDetailsData(productId: String) = withContext(Dispatchers.IO) {
        return@withContext try {
            Resource.Success(storeApi.getDetailsData(productId))
        } catch (e: HttpException){
            val error = e.response()?.errorBody()?.string()
            val errorMessage = Gson().fromJson(error, ExampleJson2KtKotlin::class.java)
            Resource.Error(errorMessage.error?.message ?: e.message ?: "An unknown error occurred")
        } catch (e: Exception){
            Resource.Error(e.message ?: "An unknown error occurred")
        }

    }

}