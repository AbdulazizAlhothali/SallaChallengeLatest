package com.example.sallachallenge.di

import android.content.Context
import com.example.sallachallenge.models.developersjson.DevelopersJson
import com.example.sallachallenge.repo.StoreRepo
import com.example.sallachallenge.repo.StoreRepoImpl
import com.example.sallachallenge.retrofit.StoreApi
import com.example.sallachallenge.utils.Constants.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideStoreApi(retrofit: Retrofit): StoreApi {
        return retrofit.create(StoreApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(storeApi: StoreApi): StoreRepo {
        return StoreRepoImpl(storeApi)
    }

    @Singleton
    @Provides
    fun jsonString(@ApplicationContext context: Context?): String {
        val json: String?
        val inputStream: InputStream = context?.assets!!.open("Developers.json")
        json = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        return json
    }

    @Singleton
    @Provides
    fun jsonDevObject(jsonString: String): DevelopersJson {
        return Gson().fromJson(jsonString, DevelopersJson::class.java)
    }
}