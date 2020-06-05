package com.example.bangkokchallenge.searchPost

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.HashTag
import com.example.bangkokchallenge.model.TimeLineItem
import com.example.bangkokchallenge.model.response.LikeResponse

class SearchPostAdapter(
    private val listener : SearchPostContract.SearchPostItemClickListener // TODO : TimeLineActivity 에서 해당 인터페이스 구현후 인자로 넘겨야 함.
) : RecyclerView.Adapter<SearchPostAdapter.TimeLineItemViewHolder>() {

    private var dataList: MutableList<TimeLineItem>? = null // datalist
    private var parentContext: Context? = null
    private lateinit var sharedPreferenceStorage: PreferenceStorage //나의 정보 꺼내오기

    fun setDataList(dataList: List<TimeLineItem>?) {
        if (this@SearchPostAdapter.dataList.isNullOrEmpty()) {
            this@SearchPostAdapter.dataList = (dataList as MutableList<TimeLineItem>)
        } else {
            dataList?.let {
                for (post in it) {
                    this@SearchPostAdapter.dataList?.add(post)
                    Log.d("addPost", "" + this@SearchPostAdapter.dataList)
                }//for
            }//let
        }//else

        notifyDataSetChanged()
    }

    fun modifyLikeData(likeResponse: LikeResponse) {
        dataList?.let {
            for (post in it) {
                if (post.id == likeResponse.postId) {
                    post.selfLike = likeResponse.likeState
                }//if
            }//for
        }//let

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_time_line, parent, false)

        parentContext = parent.context // context 여러곳에서 사용할 수 있게 저장

        return TimeLineItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TimeLineItemViewHolder, position: Int) {

        holder.apply {
            dataList?.let {
                //filepath가 그냥 문자열로 넘어온다.
                /*일단 임시로 첫째 사진만 넣고 snapshot해결한 다음 시작하는게 맞는거같다.*/
                val fileList = it[position].filePath?.split(",")

                timeLineRecyclerView.adapter = SearchPostImageAdapter(fileList)
                timeLineRecyclerView.layoutManager =
                    LinearLayoutManager(parentContext, RecyclerView.HORIZONTAL, false)


                Glide.with(profileImage.context).load(it[position].profile_photo).apply(
                    RequestOptions().circleCrop()
                ).override(300, 500)
                    .into(profileImage)

                //   Glide.with(timeLineImage.context).load(fileList?.get(0)).into(timeLineImage)

                sharedPreferenceStorage =
                    SharedPreferenceStorage(likeImage.context) //user token 넣기 위함 .. 이건 좋지 못하다 interface 만드는게 좋을거같은데 나중에  생각해보자 ..

                likeCount.text = "좋아요 " + it[position].likeCount + "개"
                userName.text = it[position].nickname
                discription.text = it[position].article
                hashTag.text = getHashTagMessages(position) // hashTag 자료형에서 content만 뽑아냄
                commentImage.text = "댓글 보기 " + it[position].commentCount + "개"
                //date.text= it[position].createdAt

                if (it[position].selfLike) {
                    Log.d("@likeTestT: ${position}", "${it[position].selfLike}")
                    likeImage.setImageDrawable(likeImage.context.getDrawable(R.drawable.like_pressed))
                } else {
                    Log.d("@likeTestF: ${position}", "${it[position].selfLike}")
                    likeImage.setImageDrawable(likeImage.context.getDrawable(R.drawable.like))
                }
            }

            likeImage.setOnClickListener {
                listener.onClickLike(dataList?.get(position)?.id, sharedPreferenceStorage.userToken)
            }
            commentImage.setOnClickListener {
                //discription 을 가지고가자
                listener.onClickComment(
                    discription.text.toString(),
                    hashTag.text.toString(),
                    dataList?.get(position)?.id
                )
            }
            moreImage.setOnClickListener {
                Log.d("이벤트리스너", "more")
            }
        }//apply

    }

    class TimeLineItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userName: TextView = itemView.findViewById(R.id.item_time_line_username)
        var profileImage: ImageView =
            itemView.findViewById(R.id.item_time_line_profile_image) //프로필 이미지
        //  var timeLineImage : ImageView = itemView.findViewById(R.id.item_time_line_image) //게시물 이미지 <- recyclerview 로 전환할 예정
        var timeLineRecyclerView: RecyclerView =
            itemView.findViewById(R.id.item_time_line_recyclerView)

        /* comment , like */
        var likeImage: ImageView = itemView.findViewById(R.id.item_time_line_like_image)
        var likeCount: TextView = itemView.findViewById(R.id.item_time_line_likeCount)
        var commentImage: TextView = itemView.findViewById(R.id.item_time_line_comment_image)

        /* article date */
        var discription: TextView = itemView.findViewById(R.id.item_time_line_discription) //article
        var hashTag: TextView = itemView.findViewById(R.id.item_time_line_hash_tag) // hashtag
        // var date: TextView = itemView.findViewById(R.id.item_time_line_date)   //createAt

        /* item */

        var moreImage: ImageView = itemView.findViewById(R.id.item_time_line_more_image)
    }

    fun getHashTagMessages(position: Int): String { //hashTag 에서 content만 뽑기
        var hashTagMessages = ""

        for (message: HashTag in dataList?.get(position)?.hashTags!!) { // hashTag 자료형에서 content만 뽑자
            Log.e("hashTag", "${message}")
            hashTagMessages += message.content + " "
        }

        return hashTagMessages
    }
}