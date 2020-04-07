package com.example.bangkokchallenge.timeline

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import kotlinx.android.synthetic.main.activity_time_line.*
import java.net.URI

class TimeLineActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_line)

        /*Glide.with(this@TimeLineActivity).load(uriPath).into(user_profile_imageview)*/
    }
}
