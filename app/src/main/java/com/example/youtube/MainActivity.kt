package com.example.youtube

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.ActivityMainBinding
import com.example.youtube.loginPopup.LoginPopupActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var shared : SharedPreferences

    // test
    private var isLogin = false
    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_layout, HomeFragment()).commit()

        binding.bottom.setOnItemSelectedListener {
            replaceFragment(
                when(it.itemId) {
                    R.id.bottom_menu_home -> HomeFragment()
                    R.id.bottom_menu_storage -> StorageFragment()
                    R.id.bottom_menu_subscribe -> SubscribeFragment()
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

    }

    override fun onResume() {
        super.onResume()

        if (isFirst){
            isFirst = false
            if (!isLogin){
                val intent = Intent(this, LoginPopupActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.vertical_enter, R.anim.none)
            }
        }
    }


    public fun replaceFragment (fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, fragment).commit()
    }
}