package com.example.bangkokchallenge.createpost

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.databinding.ActivityCreatePostBinding
import com.example.bangkokchallenge.databinding.ItemImageBinding
import com.example.bangkokchallenge.model.CreatePostDTO
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_create_post.*

class CreatePostActivity : AppCompatActivity() ,CreatePostContract.View{

    /* 데이터 바인딩 .. ? */
    private lateinit var binding: ActivityCreatePostBinding
    private var selectedUriList: List<Uri>? = null

    /* View */
    private var post_discription_text:String?=null
    private var post_hashTag_text:String?=null


    override lateinit var presenter : CreatePostContract.Presenter
    private lateinit var interactor :CreatePostContract.CreatePostInteractor
    private lateinit var sharedPreference : PreferenceStorage

    /* image uri , content, hashTag */
    private lateinit var createPostDTO : CreatePostDTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_post)

        interactor=CreatePostInteractorImpl()
        presenter=CreatePostPresenter(this@CreatePostActivity,interactor) //presenter 설정
        sharedPreference = SharedPreferenceStorage(this)

        initView()

        /* 이미지 불러오기 , 게시하기 */
        loadImage()

        //createPostDTO = CreatePostDTO(selectedUriList,post_discription_text,post_hashTag_text)

    }

    override fun posting(){

       // finish() //Todo :  설명, 해시태그, 사진 서버로 전송
    }

    private fun initView(){
        /* 설명, 해시태그, 게시하기*/
        post_discription_text=post_discription.text.toString()
        post_hashTag_text=post_hash_tag.text.toString()

        posting_btn.setOnClickListener {
            presenter.requestPosting(createPostDTO)
        }
    }

    private fun loadImage() {
            TedImagePicker.with(this)
                //.mediaType(MediaType.IMAGE)
                //.scrollIndicatorDateFormat("YYYYMMDD")
                //.buttonGravity(ButtonGravity.BOTTOM)
                //.buttonBackground(R.drawable.btn_sample_done_button)
                //.buttonTextColor(R.color.sample_yellow)
                .errorListener { message -> Log.d("ted", "message: $message") }
                .selectedUri(selectedUriList)
                .startMultiImage { list: List<Uri> -> showMultiImage(list) }

    }

    private fun showMultiImage(uriList: List<Uri>) {
        this.selectedUriList = uriList

        Log.d("@@path","{$selectedUriList}")
        binding.ivImage.visibility = View.GONE
        binding.containerSelectedPhotos.visibility = View.VISIBLE

        /* 원래 view 에 보이던 사진들을 초기화 후 선택한 사진들을 스크롤뷰에 띄우는듯 */
        binding.containerSelectedPhotos.removeAllViews() // removeAllViews ->  동적으로 이 버튼과 텍스트뷰를 모두 없애 화면을 깨끗하게 만들어야 할 경우 사용


        val viewSize = TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics)
            .toInt()

        uriList.forEach {
            val itemImageBinding = ItemImageBinding.inflate(LayoutInflater.from(this))

            Glide.with(this)
                .load(it) // it -> uri
                .apply(RequestOptions().fitCenter())
                .override(300,500)
                .into(itemImageBinding.ivMedia)
            /* LayoutParams ->  지정된 폭과 높이로 새로운 레이아웃 파라미터 세트를 작성합니다. */
            itemImageBinding.root.layoutParams = FrameLayout.LayoutParams(viewSize, viewSize)
            binding.containerSelectedPhotos.addView(itemImageBinding.root)
        }

        /* 이걸 어디다 넣어야할지 모르갯음 ㅠ 이미지가 다 선택되서 selectedUriList가 정해지는 시점이 이때. */
        createPostDTO = CreatePostDTO(sharedPreference.userToken,selectedUriList,post_discription_text,post_hashTag_text)
    }
}
