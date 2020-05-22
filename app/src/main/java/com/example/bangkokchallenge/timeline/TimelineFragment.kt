package com.example.bangkokchallenge.timeline


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse


class TimelineFragment : Fragment(), TimeLineContract.View {

    override lateinit var presenter: TimeLineContract.Presenter
    private lateinit var interactor: TimeLineContract.TimeLineInteractor
    private lateinit var adapter : TimeLineAdapter
    /*TimeLine Recyclerview*/
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: PreferenceStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.fragment_timeline, container, false)

        sharedPreferences=SharedPreferenceStorage(requireContext())
        interactor = TimeLineInteractorImpl()
        presenter = TimeLinePresenter(this@TimelineFragment, interactor)
        initViews(view)

        fetchInitData()

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.timeline_recyclerview)
        recyclerView.layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        adapter = TimeLineAdapter(presenter)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("last Post","들어옴. ${recyclerView.adapter!!.itemCount}")
                val lastPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                Log.d("lastPosition","${lastPosition}")
                val totalCount = recyclerView.adapter!!.itemCount
                if (lastPosition == totalCount) {
                    Log.d("last Post","마지막포스트입니다.")
                }
            }

        })//listener
    }

    private fun fetchInitData(){
        presenter.requestTimeLineDataFromServer(sharedPreferences.userToken)
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>?) {
        adapter.setDataList(responseData)
    }
//open, navigated

    override fun openToCommentPage(discription:String,postId : Int?) {
        var intent = Intent(requireContext(),CommentActivity::class.java)
        intent.putExtra("discription",discription) // <- 게시물에 대한 제목?설명
        intent.putExtra("postId",postId)

        startActivity(intent)
    }

    override fun onResponseFailure(t: Throwable?) {
        Toast.makeText(requireContext(), "데이터 불러오기에 실패 했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun modifyLikeData(likeResponse: LikeResponse) {
       adapter.modifyLikeData(likeResponse)
    }
}
