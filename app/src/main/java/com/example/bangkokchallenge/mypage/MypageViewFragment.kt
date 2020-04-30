package com.example.bangkokchallenge.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.login.LoginActivity
import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.PostDTO
import com.example.bangkokchallenge.model.TimeLineItem
import kotlinx.android.synthetic.main.fragment_mypage_view.*
import kotlinx.android.synthetic.main.fragment_mypage_view.view.*


class MypageViewFragment : Fragment() ,MyPageContract.View{

    override lateinit var presenter: MyPageContract.Presenter
    private lateinit var interactor: MyPageContract.MyPageInteractor
    private lateinit var sharedPreference:SharedPreferenceStorage

    /*layout item*/
    private lateinit var recyclerView:RecyclerView
    private lateinit var userProfileAccount: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      var view=LayoutInflater.from(activity).inflate(R.layout.fragment_mypage_view,container,false)

        sharedPreference= SharedPreferenceStorage(requireContext())
        initViews(view)

        /* presenter에 interactor 넘겨주기*/

        interactor=MyPageInteractorImpl()
        presenter=MyPagePresenter(this,interactor)

        presenter.requestMyPageDataFromSever() // 서버에서 data 요청

        return view
    }


    private fun initViews(view:View){
        recyclerView=view.findViewById(R.id.post_recyclerview_account)
        recyclerView.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        userProfileAccount=view.findViewById(R.id.user_profile_account)
        Glide
            .with(this)
            .load(sharedPreference.userProfileImagePath).into(userProfileAccount);
    }


    override fun setRecyclerViewData(responseData: List<TimeLineItem>) {
        recyclerView.adapter=MyPageAdapter(responseData)
    }


    override fun onResponseFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
