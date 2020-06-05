package com.example.bangkokchallenge.searchPost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse


class SearchPostFragment : Fragment(), SearchPostContract.View {

    override lateinit var presenter: SearchPostContract.Presenter
    private lateinit var interactor: SearchPostContract.SearchPostInteractor
    private lateinit var adapter : SearchPostAdapter
    /*TimeLine Recyclerview*/
    private lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: PreferenceStorage

    /* keyWord */
    private  var  searchKeyWord : String?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(activity).inflate(R.layout.fragment_timeline, container, false)
        searchKeyWord = arguments?.getString("searchKeyWord")

        sharedPreferences= SharedPreferenceStorage(requireContext())
        interactor = SearchPostInteractorImpl()
        presenter = SearchPostPresenter(this@SearchPostFragment, interactor)
        initViews(view)

        fetchInitData()

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.timeline_recyclerview)
        recyclerView.layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val totalCount = recyclerView.adapter!!.itemCount-1

                Log.d("last",""+lastPosition)
                if (lastPosition == totalCount) {
                    Log.d("last_Post","마지막포스트입니다.")
                    presenter.requestSearchPostDataFromServer(sharedPreferences.userToken,searchKeyWord)
                }
            }
        })//listener

        adapter = SearchPostAdapter(presenter)
        recyclerView.adapter = adapter
    }

    private fun fetchInitData(){
        presenter.requestSearchPostDataFromServer(sharedPreferences.userToken,searchKeyWord)
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>?) {
        adapter.setDataList(responseData)
    }
//open, navigated

    override fun openToCommentPage(discription:String,hashTag : String,postId : Int?) {
        var intent = Intent(requireContext(), CommentActivity::class.java)
        intent.putExtra("discription",discription) // <- 게시물에 대한 제목?설명
        intent.putExtra("hashTag",hashTag)
        intent.putExtra("postId",postId)

        startActivity(intent)
    }


    override fun modifyLikeData(likeResponse: LikeResponse) {
        adapter.modifyLikeData(likeResponse)
    }
}
