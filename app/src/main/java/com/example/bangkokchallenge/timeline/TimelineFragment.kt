package com.example.bangkokchallenge.timeline


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem


class TimelineFragment : Fragment(), TimeLineContract.View {

    override lateinit var presenter: TimeLineContract.Presenter
    private lateinit var interactor: TimeLineContract.TimeLineInteractor

    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.fragment_timeline, container, false)

        initViews(view)

        interactor = TimeLineInteractorImpl()
        presenter = TimeLinePresenter(this@TimelineFragment, interactor)
        presenter.requestTimeLineDataFromServer()

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.timeline_recyclerview)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    override fun setRecyclerViewData(responseData: List<TimeLineItem>) {
        val adapter = TimeLineAdapter(responseData)
        recyclerView.adapter = adapter
    }

    override fun onResponseFailure(t: Throwable?) {
        Toast.makeText(requireContext(), "데이터 불러오기에 실패 했습니다.", Toast.LENGTH_SHORT).show()
    }
}
