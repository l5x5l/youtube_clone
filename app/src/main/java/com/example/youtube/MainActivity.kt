package com.example.youtube

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.loginPopup.LoginPopupActivity
import com.example.youtube.main.data.VideoMeta
import com.example.youtube.main.data.Videos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var shared : SharedPreferences

    // login 기능 도입 전 임시
    private var isLogin = false
    private var isFirst = true

    // retrofit 관련 임시
    private lateinit var retrofit : Retrofit
    private lateinit var youtube : RetrofitYoutube
    private var retrofitVideoList : List<VideoMeta>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, HomeFragment()).commit()

        binding.bottom.setOnItemSelectedListener {
            replaceFragment(
                when(it.itemId) {
                    R.id.bottom_menu_home -> HomeFragment()
                    R.id.bottom_menu_storage -> {
                        if (isLogin){
                            StorageFragment()
                        } else {
                            storageNotLoginFragment()
                        }
                    }
                    R.id.bottom_menu_subscribe -> {
                        if(isLogin){
                            SubscribeFragment()
                        } else {
                            subscribeNotLoginFragment()
                        }
                    }
                    R.id.bottom_menu_quest -> QuestFragment()
                    else -> HomeFragment()
                }
            )
            true
        }

        binding.bottomFab.setOnClickListener{
            val bottomSheetMain = ClassBottomSheetMain()
            bottomSheetMain.show(this.supportFragmentManager, bottomSheetMain.tag)
        }

        //retrofit 관련
        /*retrofit = ClientYoutube.getInstance()
        youtube = retrofit.create(RetrofitYoutube::class.java)*/

        //loadVideo()
    }

    override fun onResume() {
        super.onResume()

        /*if (isFirst){
            isFirst = false
            if (!isLogin){
                val intent = Intent(this, LoginPopupActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.vertical_enter, R.anim.none)
            }
        }*/
    }


    public fun replaceFragment (fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, fragment).commit()
    }

    // retrofit2 테스트
    /*fun loadVideo() {
        youtube.getVideosPopular().enqueue(object: Callback<Videos>{
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful){
                    val result = response.body()

                    if (result != null) {
                        videoList = result.items
                    } else {
                        videoList = listOf()
                    }
                }
            }

            override fun onFailure(call: Call<Videos>, t: Throwable) {}

        })
    }*/
}