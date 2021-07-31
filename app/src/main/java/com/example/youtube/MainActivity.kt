package com.example.youtube

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.main.data.*
import com.example.youtube.main.movieFragment.MovieFragment
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.user.UserApiClient
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
    private var movieFragment : MovieFragment? = null

    private var previousFragment : Fragment = homeFragment

    // retrofit 관련
    private lateinit var youtubeRetrofit : Retrofit
    private lateinit var youtube : RetrofitYoutube
    private var homeVideoList : List<VideoMeta>? = null
    private var questVideoList : List<VideoMeta>? = null
    private var retrofitUserList : List<UserMeta> ?= null
    private var subscribeVideoList : List<SearchVideoMeta> ?= null

    init {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                GlobalApplication.isLogin = error == null
                /*if (error is KakaoSdkError && error.isInvalidTokenError()) {
                    //로그인 필요
                }
                else {
                    //기타 에러
                }*/
            }
        }
        else {
            GlobalApplication.isLogin = false
        }
    }

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
                    if (GlobalApplication.isLogin) {
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
                    if (GlobalApplication.isLogin) {
                        if (subscribeFragment == null){
                            subscribeFragment = SubscribeFragment()
                            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, subscribeFragment!!).commit()
                            loadSubscribes()
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
                        loadVideoQuest()
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

        /*if(!isLogin) {
            binding.bottom.menu.getItem(2).isVisible = false
            binding.bottomFab.visibility =  View.GONE
        }*/
        setBottom(GlobalApplication.isLogin)

        //retrofit 관련
        youtubeRetrofit = ClientYoutube.getInstance()
        youtube = youtubeRetrofit.create(RetrofitYoutube::class.java)

        loadVideoHome()
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

    override fun onRestart() {
        super.onRestart()
        setBottom(GlobalApplication.isLogin)
    }

    fun setBottom(flag : Boolean){
        if (flag) {
            binding.bottom.menu.getItem(2).isVisible = true
            binding.bottomFab.visibility = View.VISIBLE
        } else {
            binding.bottom.menu.getItem(2).isVisible = false
            binding.bottomFab.visibility =  View.GONE
        }
    }

    public fun replaceFragment (fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, fragment).commit()
    }

    // 홈 fragment 에서 사용할 video data
    fun loadVideoHome() {
        youtube.getVideosPopular(maxResults = 10).enqueue(object: Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful){
                    val result = response.body()
                    homeVideoList = result?.items ?: listOf()
                } else {
                    Log.d("loadVideo", "response is fail...")
                    homeVideoList = listOf()
                }
                homeFragment.videoChange(homeVideoList!!)
            }

            override fun onFailure(call: Call<Videos>, t: Throwable) {
                homeVideoList = listOf()
                Log.d("loadVideo", "onFailure...")
            }

        })
    }

    // 탐색 fragment 에서 사용할 video data
    fun loadVideoQuest() {
        youtube.getVideosPopular(maxResults = 10).enqueue(object: Callback<Videos> {
            override fun onResponse(call: Call<Videos>, response: Response<Videos>) {
                if (response.isSuccessful){
                    val result = response.body()
                    questVideoList = result?.items ?: listOf()
                } else {
                    questVideoList = listOf()
                }
                questFragment!!.videoChange(questVideoList!!)
            }

            override fun onFailure(call: Call<Videos>, t: Throwable) {
                questVideoList = listOf()
            }

        })
    }

    // 구독 fragment 에서 사용할 subscribe channel data
    fun loadSubscribes() {
        youtube.getSubscribes().enqueue(object: Callback<Users>{

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    retrofitUserList = result?.items ?: listOf()
                } else {
                    retrofitUserList = listOf()
                }
                subscribeFragment!!.userChange(retrofitUserList!!)
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                retrofitUserList = listOf()
            }
        })
    }

    fun loadChannelVideos(channelId : String) {
        youtube.getChannelVideo(channelId = channelId).enqueue(object : Callback<SearchVideos>{
            override fun onResponse(call: Call<SearchVideos>, response: Response<SearchVideos>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    subscribeVideoList = result?.items ?: listOf()
                } else {
                    subscribeVideoList = listOf()
                    Log.d("loadVideo", "response is fail...")
                }
                subscribeFragment!!.videoChange(subscribeVideoList!!)
            }

            override fun onFailure(call: Call<SearchVideos>, t: Throwable) {
                subscribeVideoList = listOf()
                Log.d("loadVideo", t.localizedMessage)
            }

        })
    }

    public fun showMovieFragment() {
        supportFragmentManager.beginTransaction().hide(previousFragment).commit()
        if (movieFragment == null){
            movieFragment = MovieFragment()
            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, movieFragment!!).commit()
        } else {
            supportFragmentManager.beginTransaction().show(movieFragment!!).commit()
        }
        previousFragment = movieFragment!!
    }

    public fun showQuestFragment() {
        supportFragmentManager.beginTransaction().hide(previousFragment).commit()
        if (questFragment == null){
            questFragment = QuestFragment()
            supportFragmentManager.beginTransaction().add(binding.fragmentLayout.id, questFragment!!).commit()
        } else {
            supportFragmentManager.beginTransaction().show(questFragment!!).commit()
        }
        previousFragment = questFragment!!
    }
}