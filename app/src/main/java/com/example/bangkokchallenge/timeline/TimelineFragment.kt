package com.example.bangkokchallenge.timeline


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.ResponseModel
import kotlinx.android.synthetic.main.item_time_line.*


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
    }

    private fun fetchInitData(){
        presenter.requestTimeLineDataFromServer(sharedPreferences.userToken)
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>?) {
        //
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

    override fun modifyLikeData(position: Int, boolean: Boolean) {
       adapter.modifyLikeData(position,boolean)
    }
}
