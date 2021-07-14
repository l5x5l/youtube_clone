package com.example.youtube

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.youtube.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentHomeBinding.inflate(inflater, container, false)

        binding.aniLayout.setOnClickListener {
            //val data = binding.videoTitle1.text.toString()
            val videoId = R.raw.ani
            val userId = R.drawable.user_keke
            val userName = binding.userName1.text
            val intent = Intent(activity, VideoActivity::class.java)
            val subtext = binding.videoSubtext1.text
            val videoTitle = binding.videoTitle1.text
            intent.putExtra("id", videoId)
            intent.putExtra("user", userId)
            intent.putExtra("userName", userName)
            intent.putExtra("subText", subtext)
            intent.putExtra("videoTitle", videoTitle)
            startActivity(intent)
        }

        binding.logoLayout.setOnClickListener {
            //val data = binding.videoTitle2.text.toString()
            val videoId = R.raw.logo
            val userId = R.drawable.user_pate
            val userName = binding.userName2.text
            val intent = Intent(activity, VideoActivity::class.java)
            val subtext = binding.videoSubtext2.text
            val videoTitle = binding.videoTitle2.text
            intent.putExtra("id", videoId)
            intent.putExtra("user", userId)
            intent.putExtra("userName", userName)
            intent.putExtra("subText", subtext)
            intent.putExtra("videoTitle", videoTitle)
            startActivity(intent)
        }

        binding.aniLayout2.setOnClickListener{
            val videoId = R.raw.ani
            val userId = R.drawable.user_keke
            val userName = binding.userName3.text
            val intent = Intent(activity, VideoActivity::class.java)
            val subtext = binding.videoSubtext3.text
            val videoTitle = binding.videoTitle3.text
            intent.putExtra("id", videoId)
            intent.putExtra("user", userId)
            intent.putExtra("userName", userName)
            intent.putExtra("subText", subtext)
            intent.putExtra("videoTitle", videoTitle)
            startActivity(intent)
        }

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}