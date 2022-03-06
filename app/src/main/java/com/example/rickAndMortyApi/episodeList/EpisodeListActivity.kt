package com.example.rickAndMortyApi.episodeList

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickAndMortyApi.R
import kotlinx.android.synthetic.main.activity_episode_list.*
import kotlinx.android.synthetic.main.activity_main.*

class EpisodeListActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: EpisodeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_list)
        initRecyclerView()
        initViewModel(EpisodeProcessor().getOutputEpisodeList())
    }

    private fun initRecyclerView() {
        episodeListRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@EpisodeListActivity)
            addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerViewAdapter = EpisodeListAdapter()
            adapter = recyclerViewAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViewModel(episodeId: String) {
        val viewModel = ViewModelProvider(this)[EpisodeListViewModel::class.java]
        viewModel.getMultipleLiveDataObserver().observe(this, {
            recyclerViewAdapter.setEpisodeList(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        if (episodeId.contains(",")) {
            viewModel.makeMultipleCall(episodeId)
        } else {
            viewModel.makeSingleCall(episodeId)
        }
    }
}