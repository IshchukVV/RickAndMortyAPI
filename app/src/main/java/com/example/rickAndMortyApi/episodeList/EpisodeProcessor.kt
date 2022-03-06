package com.example.rickAndMortyApi.episodeList

private lateinit var outputEpisodeList: String

class EpisodeProcessor {
    fun setInputEpisodeList(episodeListFromApi: List<String>?) {
        var inputEpisodeList: List<String> = emptyList()
        if (episodeListFromApi != null) {
            inputEpisodeList = episodeListFromApi
        }
        outputEpisodeList = inputEpisodeList
            .joinToString { it.removePrefix("https://rickandmortyapi.com/api/episode/") }
            .replace("\\s".toRegex(), "")
        println(outputEpisodeList)
    }

    @JvmName("getOutputEpisodeList")
    fun getOutputEpisodeList(): String {
        return outputEpisodeList
    }
}