package com.example.bangkokchallenge.likepost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bangkokchallenge.R


class LikePostFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view = LayoutInflater.from(activity).inflate(R.layout.fragment_like_post,container,false)

        return view
    }
}
