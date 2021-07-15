package com.example.youtube

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.databinding.FragmentSubscribeBinding

class SubscribeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentSubscribeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userRecyclerViewAdapter : DataUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubscribeBinding.inflate(inflater, container, false)

        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(activity as MainActivity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.userRecyclerview.layoutManager = linearLayoutManager

        var userList = ArrayList<DataUser>()
        userList.add(DataUser("LCK", R.drawable.user_lck))
        userList.add(DataUser("kekeflipnote", R.drawable.user_keke))
        userList.add(DataUser("파테슘", R.drawable.user_pate))
        userList.add(DataUser("지식해적단", R.drawable.user_pirates))
        userList.add(DataUser("flutter", R.drawable.user_flutter))

        userRecyclerViewAdapter = DataUserAdapter(activity as MainActivity, userList)
        binding.userRecyclerview.adapter = userRecyclerViewAdapter
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