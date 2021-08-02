package com.example.youtube

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentSubscribeBinding
import com.example.youtube.main.data.SearchVideoMeta
import com.example.youtube.main.data.UserMeta
import com.example.youtube.main.customAdapter.AdapterSearchVideo
import com.example.youtube.main.customAdapter.AdapterUser

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

        val videoLinearLayoutManager = LinearLayoutManager(activity as MainActivity)
        videoLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.videoRecycler.layoutManager = videoLinearLayoutManager

        binding.videoRecycler.adapter = AdapterSearchVideo(activity as MainActivity, listOf<SearchVideoMeta>())
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

    private fun videoFilter(uploader : String = "__default__") {
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
        Log.d("show all video function", "not ready...")
    }

    fun userChange(newUserdata : List<UserMeta>, profile : String? = null) {
        (binding.userRecyclerview.adapter as AdapterUser).changeDataList(newUserdata)
    }

    fun videoChange(newVideoData : List<SearchVideoMeta>, profile : String? = null) {
        (binding.videoRecycler.adapter as AdapterSearchVideo).changeDataList(newVideoData, profile)
    }
}