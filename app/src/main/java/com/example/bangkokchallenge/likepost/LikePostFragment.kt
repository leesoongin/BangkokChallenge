package com.example.bangkokchallenge.likepost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import com.example.bangkokchallenge.mypage.MyPageAdapter


class LikePostFragment : Fragment() ,LikePostContract.View{

    override lateinit var presenter : LikePostContract.Presenter
    private lateinit var interactor:LikePostContract.LikePostInteractor
    private lateinit var adapter : LikePostAdapter
    private lateinit var sharedPreference: SharedPreferenceStorage

    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view = LayoutInflater.from(activity).inflate(R.layout.fragment_like_post,container,false)

        sharedPreference= SharedPreferenceStorage(requireContext())
        interactor=LikePostInteractorImpl()
        presenter=LikePostPresenter(this,interactor)

        presenter.requestLikePostDataFromServer(sharedPreference.userToken)

        initViews(view)

        return view
    }

    private fun initViews(view:View){
        recyclerView = view.findViewById(R.id.likepost_recyclerview_account)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val totalCount = recyclerView.adapter!!.itemCount - 1

                Log.d("last", "" + lastPosition)
                if (lastPosition == totalCount) {
                    Log.d("last_Post", "마지막포스트입니다.")
                    presenter.requestLikePostDataFromServer(sharedPreference.userToken)
                }
            }
        })//listener
        adapter =  LikePostAdapter(presenter)
        recyclerView.adapter = adapter
    }
    override fun setRecyclerViewData(responseData: List<TimeLineItem>?) {
        adapter.setDataList(responseData)
    }

    override fun modifyLikeData(likeResponse: LikeResponse) {
        adapter.modifyLikeData(likeResponse)
    }

    override fun openToCommentPage(discription: String,hashTag : String, postId: Int?) {
        var intent = Intent(requireContext(), CommentActivity::class.java)
        intent.putExtra("discription",discription) // <- 게시물에 대한 제목?설명
        intent.putExtra("hashTag",hashTag)
        intent.putExtra("postId",postId)

        startActivity(intent)
    }
}
