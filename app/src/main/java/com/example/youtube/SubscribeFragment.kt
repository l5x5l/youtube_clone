package com.example.youtube

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentSubscribeBinding
import com.example.youtube.main.data.UserMeta
import com.example.youtube.main.homeFragment.AdapterUser

class SubscribeFragment : Fragment() {

    private var _binding : FragmentSubscribeBinding? = null
    private val binding get() = _binding!!
    private lateinit var videoRecyclerViewAdapter : DataVideoAdapter

    //
    lateinit var videoList : ArrayList<DataVideo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubscribeBinding.inflate(inflater, container, false)

        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showAllSubscribeUser.setOnClickListener { videoFilter() }

        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.userRecyclerview.layoutManager = linearLayoutManager

        binding.userRecyclerview.adapter = AdapterUser(activity as MainActivity, listOf<UserMeta>())

        videoList = ArrayList<DataVideo>()
        videoList.add(DataVideo("Migrating an old app to Flutter2", R.drawable.thumbnail_flutter_2, "Flutter", R.drawable.user_flutter, 10544, "1개월 전", R.raw.video_flutter_2))
        videoList.add(DataVideo("Apple Watch Pocket - 활짝 (컨셉 디자인)", R.drawable.thumbnail_pate_1, "파테슘", R.drawable.user_pate, 35099, "4개월 전", R.raw.video_pate_1))
        videoList.add(DataVideo("패키지 비동기 (이 주의 패키지)", R.drawable.thumbnail_flutter_1, "Flutter", R.drawable.user_flutter, 39229, "1년 전", R.raw.video_flutter_1))
        videoList.add(DataVideo("당신이 만족하는 모션로고를 디자인해드립니다. w/블루샤크", R.drawable.logo_thumbnail, "파테슘", R.drawable.user_pate,57836, "2년 전", R.raw.logo))
        videoList.add(DataVideo("Amitie - Animation", R.drawable.ani_thumbnail, "kekeflipnote", R.drawable.user_keke, 495833, "4년 전", R.raw.ani))

        val videoLinearLayoutManager = LinearLayoutManager(activity as MainActivity)
        videoLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.videoRecycler.layoutManager = videoLinearLayoutManager

        videoRecyclerViewAdapter = DataVideoAdapter(activity as MainActivity, videoList)
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

    fun videoFilter(uploader : String = "__default__") {
        /*if (uploader == "__default__"){
            (binding.videoRecycler.adapter as DataVideoAdapter).setDataList(videoList)
        } else {
            val filter_videoList = ArrayList<DataVideo>()

            for (i in 0 until videoList.size) {
                if(videoList[i].user_name == uploader){
                    filter_videoList.add(videoList[i])
                }
            }

            (binding.videoRecycler.adapter as DataVideoAdapter).setDataList(filter_videoList)
        }*/
        Log.d("test", "test")
    }

    fun userChange(newUserdata : List<UserMeta>) {
        (binding.userRecyclerview.adapter as AdapterUser).changeDataList(newUserdata)
    }
}