package com.example.rickAndMortyApi.characterDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickAndMortyApi.data.Character
import com.example.rickAndMortyApi.network.APIService
import com.example.rickAndMortyApi.network.RetroInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {
    var currentCharacterLiveData: MutableLiveData<Character> = MutableLiveData()

    fun getCharacter(): LiveData<Character> {
        return currentCharacterLiveData
    }

    fun loadCharacter(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiService = RetroInstance.getRetrofitInstance().create(APIService::class.java)
            currentCharacterLiveData.postValue(apiService.getCharacterById(characterId))
        }
    }
}