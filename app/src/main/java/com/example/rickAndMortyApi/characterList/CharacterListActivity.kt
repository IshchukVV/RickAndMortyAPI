package com.example.rickAndMortyApi.characterList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickAndMortyApi.characterDetail.CharacterDetailActivity
import com.example.rickAndMortyApi.R
import com.example.rickAndMortyApi.data.CharacterShortData
import com.example.rickAndMortyApi.episodeList.EpisodeProcessor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

const val CHARACTER_ID = "character id"

class CharactersListActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@CharactersListActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            recyclerViewAdapter = CharacterListAdapter{ character -> adapterOnClick(character) }
            adapter = recyclerViewAdapter
        }
    }

    private fun adapterOnClick(character: CharacterShortData) {
        EpisodeProcessor().setInputEpisodeList(character.episode)
        val intent = Intent(this, CharacterDetailActivity()::class.java)
        intent.putExtra(CHARACTER_ID, character.id)
        startActivity(intent)
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[CharacterListViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it) }
        }
    }
}