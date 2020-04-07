package com.example.bangkokchallenge.timeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bangkokchallenge.R


class TimelineViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =LayoutInflater.from(activity).inflate(R.layout.fragment_timeline_view,container,false)


        return view
    }

}
