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
            userName.text=dataList[position].nickname
            discription.text=dataList[position].article

            date.text=dataList[position].createdAt
        }
    }

    class MyPageItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userName :TextView= view.findViewById(R.id.item_time_line_username)
        var discription:TextView = view. findViewById(R.id.item_time_line_discription)

        var date : TextView = view.findViewById(R.id.item_time_line_date)
    }

}//recylcerview