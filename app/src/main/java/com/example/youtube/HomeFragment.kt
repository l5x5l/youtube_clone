package com.example.youtube

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentHomeBinding
import com.example.youtube.languagePopup.LanguagePopupActivity
import com.example.youtube.loginPopup.LoginPopupActivity
import com.example.youtube.main.data.VideoMeta
import com.example.youtube.main.customAdapter.AdapterVideo


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        binding.videoRecyclerview.adapter = AdapterVideo(activity as MainActivity, listOf<VideoMeta>())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
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

    fun videoChange(newVideoData : List<VideoMeta>, newProfileData : Map<String, String> ?= null) {
        (binding.videoRecyclerview.adapter as AdapterVideo).changeDataList(newVideoData, newProfileData)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.user -> {
                val intent = Intent(activity as MainActivity, LoginPopupActivity::class.java)
                startActivity(intent)
                (activity as MainActivity).overridePendingTransition(R.anim.vertical_enter, R.anim.none)
                true
            }
            R.id.language -> {
                val intent = Intent(activity as MainActivity, LanguagePopupActivity::class.java)
                startActivity(intent)
                (activity as MainActivity).overridePendingTransition(R.anim.vertical_enter, R.anim.none)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}