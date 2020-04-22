package com.example.bangkokchallenge.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.CommentItem

class CommentAdapter (
    private val dataList:List<CommentItem>
):RecyclerView.Adapter<CommentAdapter.CommentItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):CommentItemViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)

        return CommentItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        holder.apply {
            userName.text=dataList[position].userName
            comment.text=dataList[position].comment
        }
    }

    class CommentItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        var profileImage: ImageView = view.findViewById(R.id.comment_item_profile_image)
        var userName:TextView=view.findViewById(R.id.comment_item_user_name)
        var comment:TextView=view.findViewById(R.id.comment_item_comment)
    }


}