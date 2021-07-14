package com.example.youtube

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private lateinit var binding : FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)

        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            (activity as MainActivity).replaceFragment(StorageFragment())
            return true
        }
        else{
            return super.onOptionsItemSelected(item)
        }
    }
}