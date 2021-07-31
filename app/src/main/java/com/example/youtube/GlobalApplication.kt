package com.example.youtube

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    companion object {
        var isLogin = true
        private var instance : GlobalApplication ?= null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSdk.init(this, "--")
    }
}