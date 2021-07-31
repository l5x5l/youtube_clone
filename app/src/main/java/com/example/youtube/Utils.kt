package com.example.youtube

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import androidx.annotation.RequiresApi
import java.security.MessageDigest

fun dpToPx(context: Context, dp : Int) : Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
}

@RequiresApi(Build.VERSION_CODES.P)
fun getKeyHash(context: Context) {
    try {
        val packageInfo = (context as MainActivity).packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNING_CERTIFICATES
        )
        val signingInfo = packageInfo.signingInfo.apkContentsSigners

        for (signature in signingInfo) {
            val messageDigest = MessageDigest.getInstance("SHA")
            messageDigest.update(signature.toByteArray())
            val keyHash = String(Base64.encode(messageDigest.digest(), 0))
            Log.d("KeyHash", keyHash)
        }

    } catch (e: Exception) {
        Log.e("Exception", e.toString())
    }

}