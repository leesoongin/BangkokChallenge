package com.example.bangkokchallenge.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.TimeLineItem


class MyPageAdapter(
    private val dataList:List<TimeLineItem> // timeline의 카트뷰를 그대로 재활용 할 예정
) : RecyclerView.Adapter<MyPageAdapter.MyPageItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_time_line,parent,false)

        return MyPageItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder:MyPageItemViewHolder, position: Int) {
        holder.apply{
            userName.text=dataList[position].userName
            discription.text=dataList[position].discription
            likeCount.text="좋아요 ${dataList[position].likeCount} 개"
            commentCount.text="댓글 ${dataList[position].commentCount} 개"
            date.text=dataList[position].dateTime
        }
    }

    class MyPageItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userName :TextView= view.findViewById(R.id.item_time_line_username)
        var discription:TextView = view. findViewById(R.id.item_time_line_discription)
        var likeCount:TextView = view.findViewById(R.id.item_time_line_likecount)
        var commentCount:TextView=view.findViewById(R.id.item_time_line_commentcount)
        var date : TextView = view.findViewById(R.id.item_time_line_date)
    }

}//recylcerview