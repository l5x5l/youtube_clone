package com.example.youtube.main.movieFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.*
import com.example.youtube.databinding.FragmentMovieBinding
import com.example.youtube.databinding.ItemMovieBinding
import com.example.youtube.main.data.Movie
import com.example.youtube.main.data.MovieMeta
import retrofit2.*


class MovieFragment : Fragment() {

    private var _binding : FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var tmdbRetrofit : Retrofit
    private lateinit var tmdb : RetrofitTMDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        tmdbRetrofit = ClientTMBD.getInstance()
        tmdb = tmdbRetrofit.create(RetrofitTMDB::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewPopular.layoutManager = HorizontalLinearManager(activity as MainActivity)
        binding.recyclerviewPopular.adapter = AdapterMovie(activity as MainActivity, listOf<MovieMeta>())
        binding.recyclerviewPopular.addItemDecoration(DecorationMovie(activity as MainActivity))


        binding.recyclerviewRate.layoutManager = HorizontalLinearManager(activity as MainActivity)
        binding.recyclerviewRate.adapter = AdapterMovie(activity as MainActivity, listOf<MovieMeta>())
        binding.recyclerviewRate.addItemDecoration(DecorationMovie(activity as MainActivity))

        binding.recyclerviewUpcoming.layoutManager = HorizontalLinearManager(activity as MainActivity)
        binding.recyclerviewUpcoming.adapter = AdapterMovie(activity as MainActivity, listOf<MovieMeta>())
        binding.recyclerviewUpcoming.addItemDecoration(DecorationMovie(activity as MainActivity))

        binding.appbar.title = "영화"

        loadMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home){
            (activity as MainActivity).showQuestFragment()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun loadMovies(){
        tmdb.getPopularMovie().enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null){
                        (binding.recyclerviewPopular.adapter as AdapterMovie).loadMovie(result.results)
                    }
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {}
        })

        tmdb.getRateMovie().enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val result = response.body()
                if (result != null) {
                    (binding.recyclerviewRate.adapter as AdapterMovie).loadMovie(result.results)
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {}
        })

        tmdb.getUpcomingMovie().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val result = response.body()
                if (result != null) {
                    (binding.recyclerviewUpcoming.adapter as AdapterMovie).loadMovie(result.results)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {}

        })
    }

}