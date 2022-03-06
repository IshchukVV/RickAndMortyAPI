package com.example.rickAndMortyApi.data

data class CharacterList(
    var info: Info?,
    var results: List<CharacterShortData>
)

data class Info(
    var count: Int?,
    var pages: Int?,
    var next: String?,
    var prev: String?
)

data class CharacterShortData(
    var id: Int?,       //The id of the character.
    var image: String?, //Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
    var name: String?,  //The name of the character.
    var episode: List<String>?  //List of episodes in which this character appeared.
)