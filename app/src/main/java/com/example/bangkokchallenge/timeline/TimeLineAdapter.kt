package com.example.bangkokchallenge.timeline

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.comment.CommentActivity
import com.example.bangkokchallenge.model.HashTag
import com.example.bangkokchallenge.model.TimeLineDTO
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.ResponseModel
import java.sql.Time
import kotlin.coroutines.coroutineContext


/**
 * Created by choejun-yeong on 08/04/2020.
 */

 class TimeLineAdapter(
    private val listener : TimeLineContract.TimeLineItemClickListener // TODO : TimeLineActivity 에서 해당 인터페이스 구현후 인자로 넘겨야 함.
) : RecyclerView.Adapter<TimeLineAdapter.TimeLineItemViewHolder>(){

    private var dataList : List<TimeLineItem>? = null

    fun setDataList(dataList : List<TimeLineItem>?){
        this@TimeLineAdapter.dataList = dataList

        notifyDataSetChanged()
    }

    fun modifyLikeData(position: Int, boolean: Boolean){
        dataList?.let {
            it[position].selfLike = boolean
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_time_line,parent,false)
        return TimeLineItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TimeLineItemViewHolder, position: Int) {


        holder.apply {
            dataList?.let {

                Glide.with(profileImage.context).load(it[position].profile_photo).apply(RequestOptions().circleCrop()).override(300,500)
                    .into(profileImage)

                Glide.with(timeLineImage.context).load(it[position].filePath)
                    .into(timeLineImage)

                userName.text = it[position].nickname
                discription.text = it[position].article
                hashTag.text = getHashTagMessages(position) // hashTag 자료형에서 content만 뽑아냄
                date.text= it[position].createdAt

                if(it[position].selfLike){
                    likeImage.setImageDrawable(likeImage.context.getDrawable(R.drawable.like_pressed))
                }else{
                    likeImage.setImageDrawable(likeImage.context.getDrawable(R.drawable.like))
                }
            }

            likeImage.setOnClickListener {

                listener.onClickLike(position)
            }
            commentImage.setOnClickListener {
                //discription 을 가지고가자
                listener.onClickComment(discription.text.toString())
            }
            moreImage.setOnClickListener {
                Log.d("이벤트리스너","more")
            }
        }//apply

    }

    class TimeLineItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userName: TextView = itemView.findViewById(R.id.item_time_line_username)
        var profileImage :ImageView = itemView.findViewById(R.id.item_time_line_profile_image) //프로필 이미지
        var timeLineImage : ImageView = itemView.findViewById(R.id.item_time_line_image) //게시물 이미지

        /* comment , like */
        var likeImage: ImageView =itemView.findViewById(R.id.item_time_line_like_image)
        var commentImage:ImageView=itemView.findViewById(R.id.item_time_line_comment_image)

        /* article date */
        var discription: TextView = itemView.findViewById(R.id.item_time_line_discription) //article
        var hashTag : TextView = itemView.findViewById(R.id.item_time_line_hash_tag) // hashtag
        var date: TextView = itemView.findViewById(R.id.item_time_line_date)   //createAt

        /* item */

        var moreImage:ImageView=itemView.findViewById(R.id.item_time_line_more_image)
    }

    fun getHashTagMessages(position:Int) : String{ //hashTag 에서 content만 뽑기
        var hashTagMessages =""

        for(message : HashTag in dataList?.get(position)?.hashTag!!){ // hashTag 자료형에서 content만 뽑자
            hashTagMessages+=message.content+" "
        }

        return hashTagMessages
    }
    /*
      var id : String?, //kakao id
    var accountId : String?,
    var nickName : String?, //kakao nicname
    var profile_photo : String, // 프로필사진 url
    var filePath : String?, //or List<String>
    var article : String?, //내용
    var createdAt : String?, // 생성날짜
    var modifiedAt : String?,
    var hashTag : List<HashTag>?,
    var likeCount : Int?, //좋아요개수
    var selfLike : Boolean, //좋아요 여부
    var commentCount: Int?// 댓글개수
    * */
}