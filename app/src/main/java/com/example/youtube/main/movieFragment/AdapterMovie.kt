package com.example.youtube.main.movieFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtube.databinding.ItemMovieBinding
import com.example.youtube.main.data.MovieMeta

class AdapterMovie(private val context: Context, private var movieList : List<MovieMeta>) : RecyclerView.Adapter<AdapterMovie.ViewHolder>() {
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var binding : ItemMovieBinding

    class ViewHolder(private val binding : ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        var poster = binding.poster
        var title = binding.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movieList[position].poster_path).into(holder.poster)
        holder.title.text = movieList[position].title
    }

    override fun getItemCount(): Int = movieList.size

    fun loadMovie(newMovieList : List<MovieMeta>) {
        movieList = newMovieList
        notifyDataSetChanged()
    }
}