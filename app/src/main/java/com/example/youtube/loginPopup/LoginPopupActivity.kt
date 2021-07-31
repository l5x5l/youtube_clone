package com.example.youtube.loginPopup

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube.DataBottomSheetOption
import com.example.youtube.GlobalApplication
import com.example.youtube.R
import com.example.youtube.databinding.ActivityLoginPopupBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginPopupActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginPopupBinding
    private val context = this

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

        if(!GlobalApplication.isLogin){
            binding.kakaoLogin.setImageResource(R.drawable.kakao_login)
        } else {
            binding.kakaoLogin.setImageResource(R.drawable.kakao_logout)
        }

        binding.kakaoLogin.setOnClickListener {
            if(!GlobalApplication.isLogin){
                kakaoLogin()
            } else {
                kakaoLogout()
            }
        }


        setContentView(binding.root)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.none, R.anim.vertical_exit)
    }

    fun kakaoLogin(){
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                GlobalApplication.isLogin = true
                binding.kakaoLogin.setImageResource(R.drawable.kakao_logout)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun kakaoLogout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                GlobalApplication.isLogin = false
                binding.kakaoLogin.setImageResource(R.drawable.kakao_login)
            }
        }
    }

    fun kakaoUnlink(){
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(TAG, "연결 끊기 실패", error)
            }
            else {
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                GlobalApplication.isLogin = false
                binding.kakaoLogin.setImageResource(R.drawable.kakao_login)
            }
        }
    }
}