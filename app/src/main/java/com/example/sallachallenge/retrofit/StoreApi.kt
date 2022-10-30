package com.example.sallachallenge.retrofit

import com.example.sallachallenge.models.items.BaseStoreData
import com.example.sallachallenge.models.brand.BrandData
import com.example.sallachallenge.models.details.DetailsBase
import retrofit2.http.*

interface StoreApi {


    @GET("brands/259940351?")
    suspend fun getStoreData(@Header("Store-Identifier") header: String, @Query("page") page: Int, @Query("per_page") per_page: Int): BaseStoreData

    @GET("brands/259940351?")
    suspend fun getBrandeData(@Header("Store-Identifier") header: String): BrandData

    @GET("products/{product_id}/details")
    suspend fun getDetailsData(@Header("Store-Identifier") header: String, @Path("product_id") productId: String): DetailsBase
}