package com.example.bangkokchallenge.timeline


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem


class TimelineFragment : Fragment(), TimeLineContract.View {

    override lateinit var presenter: TimeLineContract.Presenter
    private lateinit var interactor: TimeLineContract.TimeLineInteractor

    /*TimeLine Recyclerview*/
    private lateinit var recyclerView: RecyclerView

    /*TimeLine icon item*/
    private lateinit var likeImage:ImageView
    private lateinit var commentImage:ImageView
    private lateinit var moreImage:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.fragment_timeline, container, false)

        initViews(view)
        timeLineItemEventListener()

        interactor = TimeLineInteractorImpl()
        presenter = TimeLinePresenter(this@TimelineFragment, interactor)
        presenter.requestTimeLineDataFromServer()

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.timeline_recyclerview)
        recyclerView.layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        likeImage=view.findViewById(R.id.item_time_line_like_image)
        commentImage=view.findViewById(R.id.item_time_line_comment_image)
        moreImage=view.findViewById(R.id.item_time_line_more_image)
    }

    private fun timeLineItemEventListener(){
        likeImage.setOnClickListener {
            //Todo: 색칠하트 아이콘으로 변경, 좋아요 누른 map에 계정번호 전달

        }
        commentImage.setOnClickListener {
            //Todo: discription, hashtag in intent -> 댓글 페이지로 페이지 전환
        }
        moreImage.setOnClickListener {
            //Todo: 수정, 삭제 바텀시트로
        }
    }

    override fun setLikeData() {

    }

    override fun moveCommentPage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>) {
        val adapter = TimeLineAdapter(responseData)
        recyclerView.adapter = adapter
    }

    override fun onResponseFailure(t: Throwable?) {
        Toast.makeText(requireContext(), "데이터 불러오기에 실패 했습니다.", Toast.LENGTH_SHORT).show()
    }
}
