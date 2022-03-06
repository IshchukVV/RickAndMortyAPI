package com.example.rickAndMortyApi.episodeList

import androidx.lifecycle.*
import com.example.rickAndMortyApi.data.Episode
import com.example.rickAndMortyApi.network.APIService
import com.example.rickAndMortyApi.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodeListViewModel : ViewModel() {
    var liveMultipleData: MutableLiveData<List<Episode>?> = MutableLiveData()

    fun getMultipleLiveDataObserver(): MutableLiveData<List<Episode>?> {
        return liveMultipleData
    }

    fun makeMultipleCall(episodeId: String) {
        val apiService = RetroInstance.getRetrofitInstance().create(APIService::class.java)
        val response = apiService.getEpisodeListById(episodeId)
        response.enqueue(object : Callback<List<Episode>?> {
            override fun onResponse(
                call: Call<List<Episode>?>,
                response: Response<List<Episode>?>
            ) {
                liveMultipleData.postValue(response.body())
            }
            override fun onFailure(call: Call<List<Episode>?>, t: Throwable) {

            }
        })
    }

    fun makeSingleCall(episodeId: String) {
            val apiService = RetroInstance.getRetrofitInstance().create(APIService::class.java)
            val response = apiService.getSingleEpisodeById(episodeId)
        response?.enqueue(object : Callback<Episode?>{
            override fun onResponse(call: Call<Episode?>, response: Response<Episode?>) {
                val responseList: List<Episode> = listOf(response.body()!!)
                liveMultipleData.postValue(responseList)
            }
            override fun onFailure(call: Call<Episode?>, t: Throwable) {
                println("error")
            }
        })
    }
}