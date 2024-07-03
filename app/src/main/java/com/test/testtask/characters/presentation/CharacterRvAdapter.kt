package com.test.testtask.characters.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testtask.R
import com.test.testtask.databinding.CharacterItemBinding

class CharacterRvAdapter: RecyclerView.Adapter<CharacterRvAdapter.ViewHolder>() {

    private var items: List<CharacterUiState> = emptyList();

    var clickToWorld: ((CharacterUiState)->Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var binding = CharacterItemBinding.bind(itemView)
        fun bind(character: CharacterUiState) = with(binding){
            binding.characterNameTxt.text = character.name
            binding.genderTypeTxt.text = character.gender
            binding.brithdateTxt.text = character.birthdate
            binding.root.setOnClickListener {
                clickToWorld?.invoke(character)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<CharacterUiState>){
        items = newList
        notifyDataSetChanged()
    }

}