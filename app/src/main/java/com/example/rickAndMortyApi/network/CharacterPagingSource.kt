package com.example.rickAndMortyApi.network

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickAndMortyApi.data.CharacterShortData
import java.lang.Exception

class CharacterPagingSource(private val apiService: APIService) :
    PagingSource<Int, CharacterShortData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterShortData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterShortData> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getAllCharacters(nextPage)
            var nextPageNumber: Int? = null
            if (response.info?.next != null) {
                val uri = Uri.parse(response.info?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}