package com.example.bangkokchallenge.main


import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.mypage.MypageViewFragment
import com.example.bangkokchallenge.timeline.TimelineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNav = findViewById(R.id.main_bottom_nav)
        mainBottomNav.setOnNavigationItemSelectedListener(this) //listener
        mainBottomNav.selectedItemId = R.id.action_home //default -> home
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.action_home -> {//main page
                var timelineViewFragment =
                    TimelineFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_framelayout, timelineViewFragment).commit()
                return true
            }
            R.id.action_user_info -> {//grid
                var mypageViewFragment = MypageViewFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_framelayout, mypageViewFragment).commit()
                return true
            }
            R.id.action_create_post -> { //user

                return true
            }//when
        }
        return false  //아무것도 걸리지 않았다면 false return
    }
}
