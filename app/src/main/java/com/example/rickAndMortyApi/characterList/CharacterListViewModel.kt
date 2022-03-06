package com.example.rickAndMortyApi.characterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickAndMortyApi.network.CharacterPagingSource
import com.example.rickAndMortyApi.network.RetroInstance
import com.example.rickAndMortyApi.data.CharacterShortData
import com.example.rickAndMortyApi.network.APIService
import kotlinx.coroutines.flow.Flow

class CharacterListViewModel: ViewModel() {
    var apiService: APIService = RetroInstance.getRetrofitInstance().create(
        APIService::class.java)

    fun getListData(): Flow<PagingData<CharacterShortData>>{
        return Pager (config = PagingConfig(pageSize = 34, maxSize = 200),
        pagingSourceFactory = { CharacterPagingSource(apiService) }).flow.cachedIn(viewModelScope)
    }
}