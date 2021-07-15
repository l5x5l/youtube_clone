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
        videoList.add(DataVideo("Amitie - Animation", R.drawable.ani_thumbnail, "kekeflipnote", R.drawable.user_keke, 495833, "4년 전"))
        videoList.add(DataVideo("당신이 만족하는 모션로고를 디자인해드립니다. w/블루샤크", R.drawable.logo_thumbnail, "파테슘", R.drawable.user_pate,57836, "2년 전"))

        videoRecyclerViewAdapter = DataVideoAdapter(activity as MainActivity, videoList)
        binding.videoRecyclerview.adapter = videoRecyclerViewAdapter
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