package com.example.youtube

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.youtube.databinding.FragmentSubscribeNotLoginBinding
import com.example.youtube.loginPopup.LoginPopupActivity


class subscribeNotLoginFragment : Fragment() {

    private var _binding : FragmentSubscribeNotLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSubscribeNotLoginBinding.inflate(inflater, container, false)

        binding.appbar.setLogo(R.drawable.youtube_mini)
        (activity as MainActivity).setSupportActionBar(binding.appbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginPage.setOnClickListener { goToLoginPage() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goToLoginPage(){
        val intent = Intent(activity, LoginPopupActivity::class.java)
        startActivity(intent)
        (activity as MainActivity).overridePendingTransition(R.anim.vertical_enter, R.anim.none)
    }
}