package com.example.bangkokchallenge.mypage

import android.content.Intent
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
import com.bumptech.glide.request.RequestOptions

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.login.LoginActivity
import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.PostDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse
import kotlinx.android.synthetic.main.fragment_mypage_view.*
import kotlinx.android.synthetic.main.fragment_mypage_view.view.*


class MypageFragment : Fragment(), MyPageContract.View {

    override lateinit var presenter: MyPageContract.Presenter
    private lateinit var interactor: MyPageContract.MyPageInteractor
    private lateinit var adapter : MyPageAdapter
    private lateinit var sharedPreference: SharedPreferenceStorage

    /*layout item*/
    private lateinit var recyclerView: RecyclerView
    private lateinit var userProfileAccount: ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_mypage_view, container, false)

        sharedPreference = SharedPreferenceStorage(requireContext())
        interactor = MyPageInteractorImpl()
        presenter = MyPagePresenter(this, interactor)

        initViews(view)

        presenter.requestMyPageDataFromSever(sharedPreference.userToken) // 서버에서 data 요청

        return view
    }


    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.post_recyclerview_account)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        /* user profile Image */
        userProfileAccount = view.findViewById(R.id.user_profile_account)
        Glide
            .with(this)
            .load(sharedPreference.userProfileImagePath).apply(RequestOptions().circleCrop())
            .into(userProfileAccount)

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
                    presenter.requestMyPageDataFromSever(sharedPreference.userToken)
                }
            }
        })//listener
        adapter =  MyPageAdapter(presenter)
        recyclerView.adapter = adapter
    }

    override fun modifyLikeData(likeResponse: LikeResponse) {
        adapter.modifyLikeData(likeResponse)
    }

    override fun openToCommentPage(discription: String, postId: Int?) {
        var intent = Intent(requireContext(), CommentActivity::class.java)
        intent.putExtra("discription",discription) // <- 게시물에 대한 제목?설명
        intent.putExtra("postId",postId)

        startActivity(intent)
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>?) {
        adapter.setDataList(responseData)
    }

    override fun onResponseFailure(t: Throwable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
