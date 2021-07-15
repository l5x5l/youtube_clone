package com.example.youtube

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentStorageBinding

class StorageFragment : Fragment() {

    private var _binding : FragmentStorageBinding? = null
    private val binding get() = _binding!!
    //private lateinit var binding: FragmentStorageBinding

    private lateinit var categoryAdapter: DataStorageCategoryAdapter
    private lateinit var playListRecyclerViewAdapter : DataPlaylistAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)

        //binding.history.setOnClickListener { goToHistory() }

        // listView 사용하는 부분


        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var categoryList = ArrayList<DataStorageCategory>()
        categoryList.add(DataStorageCategory("기록", R.drawable.ic_history, 0, false))
        categoryList.add(DataStorageCategory("내 동영상", R.drawable.ic_video, 0, false))
        categoryList.add(DataStorageCategory("오프라인 저장 동영상", R.drawable.ic_download, 3, true))
        categoryList.add(DataStorageCategory("내 영화", R.drawable.ic_film, 0, false))
        categoryList.add(DataStorageCategory("나중에 볼 동영상", R.drawable.ic_time, 3, false))

        categoryAdapter = DataStorageCategoryAdapter((activity as MainActivity), categoryList)
        binding.categoryListview.adapter = categoryAdapter

        //videoActivity destory될 때마다 intent로 전달하면 되지 않나...
        //여기에 데이터를 기반으로 동적으로 linear layout에 addView를 사용해 추가

        // 여긴 리사이클러뷰
        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.playListRecyclerview.layoutManager = linearLayoutManager

        var playList = ArrayList<DataPlaylist>()
        playList.add(DataPlaylist("플러터 강의", R.drawable.user_flutter, 25))
        playList.add(DataPlaylist("휴식용", R.drawable.dreams_dreams, 3))

        playListRecyclerViewAdapter = DataPlaylistAdapter(activity as MainActivity, playList)
        binding.playListRecyclerview.adapter = playListRecyclerViewAdapter
    }

    private fun goToHistory(){
        (activity as MainActivity).replaceFragment(HistoryFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}