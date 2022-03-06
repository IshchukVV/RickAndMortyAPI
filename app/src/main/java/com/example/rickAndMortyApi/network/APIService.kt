package com.example.rickAndMortyApi.network

import com.example.rickAndMortyApi.data.Character
import com.example.rickAndMortyApi.data.CharacterList
import com.example.rickAndMortyApi.data.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") query:Int): CharacterList

    @GET("character/{characterId}")
    suspend fun getCharacterById(@Path("characterId") query:Int): Character?

    @GET("episode/{episodeIdList}")
    fun getEpisodeListById(@Path("episodeIdList") query:String): Call<List<Episode>?>

    @GET("episode/{episodeId}")
    fun getSingleEpisodeById(@Path("episodeId") query:String): Call<Episode>?
}