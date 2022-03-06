package com.example.rickAndMortyApi.episodeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickAndMortyApi.R
import com.example.rickAndMortyApi.data.Episode

class EpisodeListAdapter : RecyclerView.Adapter<EpisodeListAdapter.CustomViewHolder>() {
    private var episodeList: List<Episode>? = emptyList()

    fun setEpisodeList(episodeList: List<Episode>?) {
        this.episodeList = episodeList
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val episodeTitle: TextView = view.findViewById(R.id.episode_title)
        private val episodeAirDate: TextView = view.findViewById(R.id.air_date)

        fun bind(data: Episode) {
            episodeTitle.text = data.name
            episodeAirDate.text = data.air_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_list_item_view, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        episodeList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return episodeList?.size!!
    }
}