package com.example.youtube

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.youtube.databinding.FragmentQuestBinding

class QuestFragment : Fragment() {

    private var _binding : FragmentQuestBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryRecyclerViewAdapter : DataCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestBinding.inflate(inflater, container, false)

        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var categoryList = ArrayList<DataCategory>()
        categoryList.add(DataCategory("인기", R.drawable.background_hot, R.drawable.ic_fire))
        categoryList.add(DataCategory("음악", R.drawable.background_music, R.drawable.ic_music))
        categoryList.add(DataCategory("게임", R.drawable.background_game, R.drawable.ic_game_controller))
        categoryList.add(DataCategory("영화", R.drawable.background_movie, R.drawable.ic_film))
        categoryList.add(DataCategory("학습", R.drawable.background_study, R.drawable.ic_bulb))
        categoryList.add(DataCategory("스포츠", R.drawable.background_exercise, R.drawable.ic_sport))

        val gridLayoutManager = GridLayoutManager(activity as MainActivity, 2)
        binding.categoryRecycler.layoutManager = gridLayoutManager

        categoryRecyclerViewAdapter = DataCategoryAdapter(activity as MainActivity, categoryList)
        binding.categoryRecycler.adapter = categoryRecyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}