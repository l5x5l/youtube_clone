package com.example.youtube

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var videoRecyclerViewAdapter : DataVideoAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentHomeBinding.inflate(inflater, container, false)

        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.videoRecyclerview.layoutManager = linearLayoutManager

        var videoList = ArrayList<DataVideo>()
        videoList.add(DataVideo("Amitie - Animation", R.drawable.ani_thumbnail, "kekeflipnote", R.drawable.user_keke, 495833, "4년 전", R.raw.ani))
        videoList.add(DataVideo("당신이 만족하는 모션로고를 디자인해드립니다. w/블루샤크", R.drawable.logo_thumbnail, "파테슘", R.drawable.user_pate,57836, "2년 전", R.raw.logo))

        videoRecyclerViewAdapter = DataVideoAdapter(activity as MainActivity, videoList)
        binding.videoRecyclerview.adapter = videoRecyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPause() {
        super.onPause()
        //Toast.makeText(activity, "testing!!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // ::goTovideoActivity 를 사용해서 함수를 안자로 전달할 수 있다.
    fun goToVideoActivity(user_name : String, user_profile : Int, video_id : Int, video_title : String, video_info : String) : Unit {
        val intent = Intent(activity, VideoActivity::class.java)
        intent.putExtra("id", video_id)
        intent.putExtra("user", user_profile)
        intent.putExtra("userName", user_name)
        intent.putExtra("subText", video_info)
        intent.putExtra("videoTitle", video_title)
        startActivity(intent)
    }

}