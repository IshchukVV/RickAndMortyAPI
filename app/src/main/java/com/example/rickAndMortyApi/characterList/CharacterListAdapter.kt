package com.example.rickAndMortyApi.characterList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickAndMortyApi.R
import com.example.rickAndMortyApi.data.CharacterShortData

class CharacterListAdapter(private val onClick: (CharacterShortData)-> Unit) :
    PagingDataAdapter<CharacterShortData, CharacterListAdapter.CharacterViewHolder>(
        CharacterDifUtilCallBack
    ) {

    class CharacterViewHolder(itemView: View, val onClick: (CharacterShortData)->Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val characterName: TextView = itemView.findViewById(R.id.character_name)
        private val characterImage: ImageView = itemView.findViewById(R.id.character_image)
        private var currentCharacter: CharacterShortData? = null

        init{
            itemView.setOnClickListener{
                currentCharacter?.let{
                    onClick(it)
                }
            }
        }

        fun bind(item: CharacterShortData?) {
            currentCharacter=item
            characterName.text = item?.name
            Glide.with(characterImage)
                .load(item?.image)
                .circleCrop()
                .into(characterImage)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_item_view, parent, false)
        return CharacterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(characterViewHolder: CharacterViewHolder, position: Int) {
        characterViewHolder.bind(getItem(position))
    }


    object CharacterDifUtilCallBack : DiffUtil.ItemCallback<CharacterShortData>() {
        override fun areItemsTheSame(
            oldItem: CharacterShortData,
            newItem: CharacterShortData
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CharacterShortData,
            newItem: CharacterShortData
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }
}