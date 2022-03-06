package com.example.rickAndMortyApi.data

data class Character(
    var image: String?,         //Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
    var name: String?,          //The name of the character.
    var location: Location?,    //Name and link to the character's last known location endpoint.
    var species: String?,       //The species of the character.
    var type: String?,          //The type or subspecies of the character.
    var status: String?         //The status of the character ('Alive', 'Dead' or 'unknown').
)

data class Location(
    var name: String?           //The name of the location.
)
