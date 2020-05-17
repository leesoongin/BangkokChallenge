package com.example.bangkokchallenge.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.response.CommentResponse

class CommentAdapter (
    private val dataList:List<CommentResponse>
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
            dataList.let {
                Glide.with(profileImage.context).load(it[position].profile_photo).apply(RequestOptions().circleCrop()).override(300,500)
                    .into(profileImage)

                userName.text=it[position].nickname
                comment.text=it[position].content
            }//let
        }//apply
    }

    class CommentItemViewHolder(view:View):RecyclerView.ViewHolder(view){
        var profileImage: ImageView = view.findViewById(R.id.comment_item_profile_image)
        var userName:TextView=view.findViewById(R.id.comment_item_user_name)
        var comment:TextView=view.findViewById(R.id.comment_item_comment)
    }


}