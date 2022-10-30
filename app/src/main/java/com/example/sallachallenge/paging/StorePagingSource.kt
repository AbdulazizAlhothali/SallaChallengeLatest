package com.example.sallachallenge.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sallachallenge.models.items.Data
import com.example.sallachallenge.retrofit.StoreApi
import retrofit2.HttpException
import java.io.IOException

class StorePagingSource(private val storeApi: StoreApi, private val header: String) : PagingSource<Int, Data>(){
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val position = params.key ?: 1
            val response = storeApi.getStoreData(header,position, params.loadSize)
            return LoadResult.Page(
                data = response.data,
                prevKey = if (response.cursor.previous == null) null else position - 1,
                nextKey = if(response.cursor.next == null) null else position + 1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}