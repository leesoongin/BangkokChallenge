package com.example.bangkokchallenge.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.createpost.CreatePostActivity
import com.example.bangkokchallenge.likepost.LikePostFragment
import com.example.bangkokchallenge.mypage.MypageFragment
import com.example.bangkokchallenge.searchPost.SearchPostFragment
import com.example.bangkokchallenge.timeline.TimelineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainBottomNav : BottomNavigationView
    private lateinit var mainToolbar : androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNav = findViewById(R.id.main_bottom_nav)
        mainToolbar =findViewById(R.id.main_toolbar)
        setSupportActionBar(mainToolbar)

        mainBottomNav.setOnNavigationItemSelectedListener(this) //listener
        mainBottomNav.selectedItemId = R.id.action_home //default -> home
        //test()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_toolbar_searchview,menu)

        var searchView =(menu?.findItem(R.id.main_toolbar_searchview)?.actionView) as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = "검색어를 입력해주세요."
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(s: String): Boolean { //검색 입력시 이벤트 제어
                Log.d("input","입력중")
                return false
            }

            override fun onQueryTextSubmit(s: String): Boolean { //검색완료시 이벤트 제어
                var searchPostFragment = SearchPostFragment()
                val bundle = Bundle()

                bundle.putString("searchKeyWord",s)
                searchPostFragment.arguments =bundle

                supportFragmentManager.beginTransaction().replace(R.id.main_framelayout,searchPostFragment).commit()
                searchView.setQuery("",true) // true -> query 초기화  /  false -> query 초기화
                return false
            }
        })

        return true
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
                var mypageViewFragment = MypageFragment()

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
