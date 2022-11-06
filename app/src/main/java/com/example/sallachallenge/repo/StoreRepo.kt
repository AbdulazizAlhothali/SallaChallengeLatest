package com.example.sallachallenge.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.sallachallenge.models.items.Data
import com.example.sallachallenge.models.brand.BrandData
import com.example.sallachallenge.models.details.DetailsBase
import com.example.sallachallenge.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StoreRepo {
    fun getStoreData(): Flow<PagingData<Data>>

    suspend fun getBrandData(): Resource<BrandData>

    suspend fun getDetailsData(productId: String): Resource<DetailsBase>
}