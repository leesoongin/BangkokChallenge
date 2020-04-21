package com.example.bangkokchallenge.likepost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem
import org.w3c.dom.Text

class LikePostAdapter(
    private val dataList: List<TimeLineItem> // 좋아요한 게시물 list 타임라인 item 그대로 사용할 예정
) : RecyclerView.Adapter<LikePostAdapter.LikePostItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikePostItemViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_time_line, parent, false) // item time line 을 ..

        return LikePostItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: LikePostItemViewHolder, position: Int) {
        holder.apply {
            userName.text=dataList[position].userName
            discription.text=dataList[position].discription
            likeCount.text="좋아요 ${dataList[position].likeCount} 개"
            commentCount.text="댓글 ${dataList[position].commentCount} 개"
            date.text=dataList[position].dateTime // dateTime
        }
    }

    class LikePostItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var userName : TextView =view . findViewById(R.id.item_time_line_username)
        var discription :TextView = view.findViewById(R.id.item_time_line_discription)
        var likeCount : TextView=view.findViewById(R.id.item_time_line_likecount)
        var commentCount:TextView=view.findViewById(R.id.item_time_line_commentcount)
        var date:TextView =view .findViewById(R.id.item_time_line_date)
    }
}