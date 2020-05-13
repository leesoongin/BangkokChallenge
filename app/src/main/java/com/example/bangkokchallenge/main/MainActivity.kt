package com.example.bangkokchallenge.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.createpost.CreatePostActivity
import com.example.bangkokchallenge.likepost.LikePostFragment
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.response.ResponseModel
import com.example.bangkokchallenge.mypage.MypageViewFragment
import com.example.bangkokchallenge.timeline.TimelineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNav = findViewById(R.id.main_bottom_nav)
        mainBottomNav.setOnNavigationItemSelectedListener(this) //listener
        mainBottomNav.selectedItemId = R.id.action_home //default -> home
        //test()
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
                startActivity(Intent(applicationContext,CreatePostActivity::class.java)) // 임시 .    /  화면전환
                return true
            }//when
            R.id.action_like_post->{
                var likePostFragment =LikePostFragment()

                supportFragmentManager.beginTransaction().replace(R.id.main_framelayout,likePostFragment).commit()
                return true
            }
        }
        return false  //아무것도 걸리지 않았다면 false return
    }

   /* fun test(){
        val json = try {

            val stream = assets.open("exam.json")
            val size = stream.available();
            val buffer =  ByteArray(size)
            stream.read(buffer);
            stream.close()
            String(buffer,Charsets.UTF_8);

        } catch ( e : IOException) {
            e.printStackTrace()
        }

        val gson = Gson()
        val jsonType = object : TypeToken<ResponseModel<TimeLineDTO>>() {}.type
        val result : ResponseModel<TimeLineDTO> = gson.fromJson(json.toString(), jsonType)

        Log.d("@@@Json"," ${result._embedded.postList!![0]}")



    }*/
}
