package com.example.youtube

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    private var homeFragment : HomeFragment = HomeFragment()
    private var questFragment : QuestFragment? = null
    private var subscribeFragment : SubscribeFragment? = null
    private var subscribeNotLoginFragment : subscribeNotLoginFragment? = null
    private var storageFragment : StorageFragment? = null
    private var storageNotLoginFragment : storageNotLoginFragment? = null

    private var previousFragment : Fragment = homeFragment

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

        supportFragmentManager.beginTransaction().add(R.id.fragment_layout, homeFragment).commit()

        binding.bottom.setOnItemSelectedListener {

            supportFragmentManager.beginTransaction().hide(previousFragment).commit()

            when (it.itemId) {
                R.id.bottom_menu_home -> {
                    supportFragmentManager.beginTransaction().show(homeFragment).commit()
                    previousFragment = homeFragment
                }
                R.id.bottom_menu_storage -> {
                    if (isLogin) {
                        if (storageFragment == null){
                            storageFragment = StorageFragment()
                            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, storageFragment!!).commit()
                        } else {
                            supportFragmentManager.beginTransaction().show(storageFragment!!).commit()
                        }
                        previousFragment = storageFragment!!
                    } else {
                        if (storageNotLoginFragment == null){
                            storageNotLoginFragment = storageNotLoginFragment()
                            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, storageNotLoginFragment!!).commit()
                        } else {
                            supportFragmentManager.beginTransaction().show(storageNotLoginFragment!!).commit()
                        }
                        previousFragment = storageNotLoginFragment!!
                    }
                }
                R.id.bottom_menu_subscribe -> {
                    if (isLogin) {
                        if (subscribeFragment == null){
                            subscribeFragment = SubscribeFragment()
                            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, subscribeFragment!!).commit()
                        } else {
                            supportFragmentManager.beginTransaction().show(subscribeFragment!!).commit()
                        }
                        previousFragment = subscribeFragment!!
                    } else {
                        if (subscribeNotLoginFragment == null){
                            subscribeNotLoginFragment = subscribeNotLoginFragment()
                            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, subscribeNotLoginFragment!!).commit()
                        } else {
                            supportFragmentManager.beginTransaction().show(subscribeNotLoginFragment!!).commit()
                        }
                        previousFragment = subscribeNotLoginFragment!!
                    }
                }
                R.id.bottom_menu_quest -> {
                    if (questFragment == null){
                        questFragment = QuestFragment()
                        supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, questFragment!!).commit()
                    } else {
                        supportFragmentManager.beginTransaction().show(questFragment!!).commit()
                    }
                    previousFragment = questFragment!!
                }
                else -> {
                    supportFragmentManager.beginTransaction().show(homeFragment).commit()
                    previousFragment = homeFragment
                }
            }

            true
        }

        binding.bottomFab.setOnClickListener{
            val bottomSheetMain = ClassBottomSheetMain()
            bottomSheetMain.show(this.supportFragmentManager, bottomSheetMain.tag)
        }

        //retrofit 관련
        retrofit = ClientYoutube.getInstance()
        youtube = retrofit.create(RetrofitYoutube::class.java)

        loadVideo()
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
    fun loadVideo() {
        youtube.getVideosPopular().enqueue(object: Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful){
                    val result = response.body()

                    if (result != null) {
                        retrofitVideoList = result.items
                    } else {
                        retrofitVideoList = listOf()
                    }
                }
                homeFragment.videoChange(retrofitVideoList!!)
            }

            override fun onFailure(call: Call<Videos>, t: Throwable) {}

        })
    }
}