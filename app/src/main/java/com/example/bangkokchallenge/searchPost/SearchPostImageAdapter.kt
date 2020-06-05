package com.example.bangkokchallenge.searchPost

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.GlideApp
import com.example.bangkokchallenge.R

class SearchPostImageAdapter ( val filePathList : List<String>? )
    : RecyclerView.Adapter<SearchPostImageAdapter.TimeLinePostImageViewHolder>(){

    private var parentContext : Context? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLinePostImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_time_line_post,parent,false)

        parentContext=parent.context

        return TimeLinePostImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filePathList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TimeLinePostImageViewHolder, position: Int) {
        holder.apply {

            filePathList.let {
                Log.e("adapter","${it?.get(position)}")
                GlideApp.with(parentContext!!).load(it?.get(position))
                    .into(timeLineImage)
//                GlideApp.with(parentContext!!).load(it?.get(0))
//                    .into(timeLineImage)

            }
        }
    }

    class TimeLinePostImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timeLineImage  : ImageView = itemView.findViewById(R.id.item_time_line_image)
    }

}