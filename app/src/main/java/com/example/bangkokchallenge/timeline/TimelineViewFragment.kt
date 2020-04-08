package com.example.bangkokchallenge.timeline


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.PostDTO
import kotlinx.android.synthetic.main.fragment_timeline_view.view.*
import kotlinx.android.synthetic.main.item_post.view.*


class TimelineViewFragment : Fragment() ,TimeLineContract.View {

    lateinit var contentList:ArrayList<PostDTO> // content에 들어갈 정보 list
    override lateinit var presenter: TimeLineContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =LayoutInflater.from(activity).inflate(R.layout.fragment_timeline_view,container,false)

        contentList= arrayListOf(
            PostDTO("profile","nickname",0,0,"des","date"),
            PostDTO("profile","name2",2,3,"","postdate2")
        )

        presenter=TimeLinePresenter(this)

        view.timeline_recyclerview.adapter=TimelineViewRecyclerViewAdapter()
        view.timeline_recyclerview.layoutManager=LinearLayoutManager(activity)

        return view
    }//oncreate

    override fun pushLike() {
        Log.d("push","좋아요")
    }

    override fun pushComment() {
        Log.d("push","댓글")
    }

    override fun pushMoreview() {
        Log.d("push","상세보기")
    }

    inner class TimelineViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

            return CostomViewHolder(view)
        }

        inner class CostomViewHolder(view: View) : RecyclerView.ViewHolder(view)

        override fun getItemCount(): Int {
            return contentList.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as CostomViewHolder).itemView

        }
    }


}
