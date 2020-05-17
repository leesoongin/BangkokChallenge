package com.example.bangkokchallenge.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.model.response.CommentResponse

class CommentActivity : AppCompatActivity() ,CommentContract.View {
    /* presenter interactor */
    override lateinit var presenter:CommentContract.Presenter
    private lateinit var interactor:CommentContract.CommentInteractor

    /* sharedPreference */
    private lateinit var sharedPreference : PreferenceStorage

    private lateinit var recyclerView:RecyclerView
    private lateinit var discription:TextView
    private lateinit var backBtn:ImageView
    private lateinit var sendBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        initViews()

        interactor=CommentInteractorImpl()
        presenter=CommentPresenter(this,interactor)
        sharedPreference = SharedPreferenceStorage(this)


        presenter.requestCommentDataFromServer(sharedPreference.userToken)
    }

    override fun setRecyclerViewData(responseData: List<CommentResponse>) {
        recyclerView.adapter=CommentAdapter(responseData)
    }

    override fun closeCommentPage() {
        finish()
    }

    fun initViews(){
        recyclerView=findViewById(R.id.comment_recyclerview)
        recyclerView.layoutManager=LinearLayoutManager(applicationContext, RecyclerView.VERTICAL,false)

        discription=findViewById(R.id.comment_discription)
        discription.text=intent.getStringExtra("discription")

        backBtn=findViewById(R.id.back_btn)
        backBtn.setOnClickListener {
            presenter.requestCloseCommentPage()
        }

        sendBtn=findViewById(R.id.comment_button_sendButton)
        sendBtn.setOnClickListener {
            presenter.requestSendCommentDataToServer(sharedPreference.userToken)
        }
    }//initViews
}
