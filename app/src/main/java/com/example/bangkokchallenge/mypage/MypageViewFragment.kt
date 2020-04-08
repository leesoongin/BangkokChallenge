package com.example.bangkokchallenge.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.PostDTO
import kotlinx.android.synthetic.main.fragment_mypage_view.*
import kotlinx.android.synthetic.main.fragment_mypage_view.view.*


class MypageViewFragment : Fragment() ,MyPageContract.View{

    override lateinit var presenter: MyPageContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      var view=LayoutInflater.from(activity).inflate(R.layout.fragment_mypage_view,container,false)

        presenter=MyPagePresenter(this)

        view.post_recyclerview_account.adapter=MyPageViewRecyclerViewAdapter()
        view.post_recyclerview_account.layoutManager=GridLayoutManager(activity,3)

        return view
    }

    /*어댑터를 따로 클래스를 만들까요 inner class로 사용할까요? */
   inner class MyPageViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            /*임시. 게시물 이미지만 보이게해야할지 게시물 item을 만들지 생각해봐야할듯*/
            val width = resources.displayMetrics.widthPixels / 3
            val imageView = ImageView(parent.context)

            imageView.layoutParams = LinearLayoutCompat.LayoutParams(width, width)

            return CustomViewHolder(imageView)
        }

        override fun getItemCount(): Int {
           //받아온 객체 size만큼 return
            return 0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder=(holder as CustomViewHolder).imageview

        }

       inner class CustomViewHolder(var imageview:ImageView) :RecyclerView.ViewHolder(imageview)
    }//recylcerview
}
