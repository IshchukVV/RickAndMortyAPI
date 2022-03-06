package com.example.rickAndMortyApi.characterDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickAndMortyApi.R
import com.example.rickAndMortyApi.characterList.CHARACTER_ID
import com.example.rickAndMortyApi.episodeList.EpisodeListActivity
import kotlin.properties.Delegates

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)
        var characterId by Delegates.notNull<Int>()
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            characterId = bundle.getInt(CHARACTER_ID)
        }
        val characterImage: ImageView = findViewById(R.id.image)
        val characterName: TextView = findViewById(R.id.name)
        val characterLocation: TextView = findViewById(R.id.location)
        val characterSpice: TextView = findViewById(R.id.spice)
        val characterStatus: TextView = findViewById(R.id.status)
        val getEpisodeBtn: Button = findViewById(R.id.getEpisodeBtn)

        getEpisodeBtn.setOnClickListener {
            val intent = Intent(this, EpisodeListActivity()::class.java)
            startActivity(intent)
        }

        initViewModel(
            characterId,
            characterImage,
            characterName,
            characterLocation,
            characterSpice,
            characterStatus
        )
    }


    private fun initViewModel(
        characterId: Int,
        image: ImageView,
        name: TextView,
        location: TextView,
        spice: TextView,
        status: TextView,
    ) {
        val viewModel = ViewModelProvider(this)[CharacterDetailViewModel::class.java]
        viewModel.getCharacter().observe(this, {
            Glide.with(image)
                .load(it.image)
                .into(image)
            name.text = it.name
            location.text = it.location?.name
            spice.text = it.species
            status.text = it.status + " - "
        })
        viewModel.loadCharacter(characterId)
    }
}