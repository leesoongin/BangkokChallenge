package com.example.bangkokchallenge.timeline

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.mypage.MypageViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_time_line.*
import kotlinx.android.synthetic.main.item_post.*
import java.net.URI

class TimeLineActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        main_bottom.setOnNavigationItemSelectedListener (this) //listener
        main_bottom.selectedItemId = R.id.action_home //default -> home
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.action_home -> {//main page
                var timelineViewFragment=TimelineViewFragment()

                supportFragmentManager.beginTransaction().replace(R.id.timeline_framelayout, timelineViewFragment).commit()
                return true
            }
            R.id.action_user_info -> {//grid
                var mypageViewFragment=MypageViewFragment()

                supportFragmentManager.beginTransaction().replace(R.id.timeline_framelayout,mypageViewFragment).commit()
                return true
            }
            R.id.action_create_post -> { //user

                return true
            }//when
        }
        return false  //아무것도 걸리지 않았다면 false return
    }

}
