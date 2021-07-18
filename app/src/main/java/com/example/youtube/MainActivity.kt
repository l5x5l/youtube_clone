package com.example.youtube

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.youtube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var shared : SharedPreferences

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

    public fun replaceFragment (fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, fragment).commit()
    }
}