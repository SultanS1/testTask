package com.test.testtask.movies.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.testtask.R
import com.test.testtask.databinding.MovieItemBinding

class MoviesRvAdapter: RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {

    private var items: List<MoviesUiState> = emptyList();

    var clickToDetails: ((MoviesUiState)->Unit)? = null


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var binding = MovieItemBinding.bind(itemView)
        fun bind(movie: MoviesUiState) = with(binding){
            binding.movieNameTxt.text = movie.filmName
            binding.directorNameTxt.text = movie.directorName
            binding.producerNameTxt.text = movie.producerName
            binding.dateInfoTxt.text = movie.date
            binding.root.setOnClickListener {
                clickToDetails?.invoke(movie)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<MoviesUiState>){
        items = newList
        notifyDataSetChanged()
    }

}