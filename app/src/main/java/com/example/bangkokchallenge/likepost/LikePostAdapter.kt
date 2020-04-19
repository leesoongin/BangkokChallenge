package com.example.bangkokchallenge.likepost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.LikePostItem

class LikePostAdapter(
    private val dataList: List<LikePostItem> // 좋아요한 게시물 list
) : RecyclerView.Adapter<LikePostAdapter.LikePostItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikePostItemViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_like_post, parent, false)

        return LikePostItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: LikePostItemViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class LikePostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}