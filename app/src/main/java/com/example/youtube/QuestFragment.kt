package com.example.youtube

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentQuestBinding
import com.example.youtube.main.data.VideoMeta
import com.example.youtube.main.customAdapter.AdapterVideo

class QuestFragment : Fragment() {

    private var _binding : FragmentQuestBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryRecyclerViewAdapter : DataCategoryAdapter
    private lateinit var videoRecyclerViewAdapter : AdapterVideo

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

        binding.categoryRecycler.addItemDecoration(DataCategoryDecoration(activity as MainActivity))

        /*var videoList = ArrayList<DataVideo>()
        videoList.add(DataVideo("Amitie - Animation", R.drawable.ani_thumbnail, "kekeflipnote", R.drawable.user_keke, 495833, "4년 전", R.raw.ani))
        videoList.add(DataVideo("패키지 비동기 (이 주의 패키지)", R.drawable.thumbnail_flutter_1, "Flutter", R.drawable.user_flutter, 39229, "1년 전", R.raw.video_flutter_1))
        videoList.add(DataVideo("Apple Watch Pocket - 활짝 (컨셉 디자인)", R.drawable.thumbnail_pate_1, "파테슘", R.drawable.user_pate, 35099, "4개월 전", R.raw.video_pate_1))
        videoList.add(DataVideo("Migrating an old app to Flutter2", R.drawable.thumbnail_flutter_2, "Flutter", R.drawable.user_flutter, 10544, "1개월 전", R.raw.video_flutter_2))
        */
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        binding.videoRecycler.layoutManager = linearLayoutManager

        //videoRecyclerViewAdapter = DataVideoAdapter(activity as MainActivity, videoList)
        videoRecyclerViewAdapter = AdapterVideo(activity as MainActivity, listOf<VideoMeta>())
        binding.videoRecycler.adapter = videoRecyclerViewAdapter
        binding.videoRecycler.isNestedScrollingEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun videoChange(newVideoData : List<VideoMeta>, newProfileData : Map<String, String> ?= null) {
        (binding.videoRecycler.adapter as AdapterVideo).changeDataList(newVideoData, newProfileData)
    }


}