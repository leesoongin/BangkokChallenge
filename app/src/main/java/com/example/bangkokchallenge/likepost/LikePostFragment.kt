package com.example.bangkokchallenge.likepost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem


class LikePostFragment : Fragment() ,LikePostContract.View{

    override lateinit var presenter : LikePostContract.Presenter
    private lateinit var interactor:LikePostContract.LikePostInteractor

    private lateinit var recyclerView:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view = LayoutInflater.from(activity).inflate(R.layout.fragment_like_post,container,false)

        initViews(view)

        interactor=LikePostInteractorImpl()
        presenter=LikePostPresenter(this,interactor)

        presenter.requestDataFromServer()

        return view
    }

    private fun initViews(view:View){
        recyclerView=view.findViewById(R.id.likepost_recyclerview_account)
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
    }
    override fun setRecyclerViewData(responseData: List<TimeLineItem>) {
        recyclerView.adapter=LikePostAdapter(responseData)
    }
}
