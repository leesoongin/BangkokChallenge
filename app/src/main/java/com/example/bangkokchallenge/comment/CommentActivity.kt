package com.example.bangkokchallenge.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.model.CommentItem
import org.jetbrains.annotations.Contract
import org.w3c.dom.Comment

class CommentActivity : AppCompatActivity() ,CommentContract.View {
/*   comment_discription
    comment_discription_divider
    comment_recyclerview*/

    override lateinit var presenter:CommentContract.Presenter
    private lateinit var interactor:CommentContract.CommentInteractor

    private lateinit var recyclerView:RecyclerView
    private lateinit var discription:TextView
    private lateinit var backBtn:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        initViews()

        interactor=CommentInteractorImpl()
        presenter=CommentPresenter(this,interactor)

        presenter.requestCommentDateFromServer()
    }

    override fun setRecyclerViewData(responseData: List<CommentItem>) {
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
    }//initViews
}
