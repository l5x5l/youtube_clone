package com.example.youtube.loginPopup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.DataBottomSheetOption
import com.example.youtube.R
import com.example.youtube.databinding.ActivityLoginPopupBinding

class LoginPopupActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPopupBinding.inflate(layoutInflater)

        val dataList = ArrayList<DataBottomSheetOption>()
        dataList.add(DataBottomSheetOption(R.drawable.ic_setting, "설정"))
        dataList.add(DataBottomSheetOption(R.drawable.ic_help_center, "고객센터"))

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = loginPopupAdapter(this, dataList)
        binding.recyclerView.isNestedScrollingEnabled = false

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.none, R.anim.vertical_exit)
    }
}