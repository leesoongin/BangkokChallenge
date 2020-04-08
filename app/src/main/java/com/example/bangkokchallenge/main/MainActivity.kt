package com.example.bangkokchallenge.timeline


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem

class TimeLineActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener {

<<<<<<< Updated upstream

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

=======
class TimeLineActivity : AppCompatActivity() , TimeLineContract.View{

    override lateinit var presenter: TimeLineContract.Presenter
    private lateinit var interactor : TimeLineContract.TimeLineInteractor

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        initViews()

        interactor = TimeLineInteractorImpl()
        presenter = TimeLinePresenter(this@TimeLineActivity,interactor)
        presenter.requestTimeLineDataFromServer()

    }

    private fun initViews(){
        recyclerView = findViewById(R.id.timeline_recyclerview)
        val layoutManager = LinearLayoutManager(this@TimeLineActivity, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>) {
        val adapter = TimeLineAdapter(responseData)
        recyclerView.adapter = adapter
    }

    override fun onResponseFailure(t: Throwable?) {
        Toast.makeText(this@TimeLineActivity,"데이터 불러오기에 실패 했습니다.",Toast.LENGTH_SHORT).show()
    }



>>>>>>> Stashed changes
}
