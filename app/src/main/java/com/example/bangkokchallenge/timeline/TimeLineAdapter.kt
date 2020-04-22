package com.example.bangkokchallenge.timeline

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem


/**
 * Created by choejun-yeong on 08/04/2020.
 */

 class TimeLineAdapter(
    private val dataList : List<TimeLineItem>
//    private val listener : TimeLineContract.TimeLineItemClickListener // TODO : TimeLineActivity 에서 해당 인터페이스 구현후 인자로 넘겨야 함.
) : RecyclerView.Adapter<TimeLineAdapter.TimeLineItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_time_line,parent,false)
        return TimeLineItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TimeLineItemViewHolder, position: Int) {
        holder.apply {
            userName.text = dataList[position].userName
            description.text = dataList[position].discription
            date.text = dataList[position].dateTime
            likeCount.text = "좋아요 ${dataList[position].likeCount}개"
            commentCount.text = "댓글 ${dataList[position].commentCount}개"

            likeImage.setOnClickListener {
                Log.d("이벤트리스너","like")
            }
            commentImage.setOnClickListener {
                Log.d("이벤트리스너","comment")
            }
            moreImage.setOnClickListener {
                Log.d("이벤트리스너","more")
            }
        }//apply

    }


    class TimeLineItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.item_time_line_username)
        var description: TextView = itemView.findViewById(R.id.item_time_line_discription)
        var likeCount: TextView = itemView.findViewById(R.id.item_time_line_likecount)
        var commentCount: TextView = itemView.findViewById(R.id.item_time_line_commentcount)
        var date: TextView = itemView.findViewById(R.id.item_time_line_date)

        /* item */
        var likeImage: ImageView =itemView.findViewById(R.id.item_time_line_like_image)
        var commentImage:ImageView=itemView.findViewById(R.id.item_time_line_comment_image)
        var moreImage:ImageView=itemView.findViewById(R.id.item_time_line_more_image)
    }
}