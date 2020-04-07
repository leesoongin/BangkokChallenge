package com.example.bangkokchallenge.login


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.bangkokchallenge.BuildConfig
import com.example.bangkokchallenge.R
import com.example.bangkokchallenge.data.local.PreferenceStorage
import com.example.bangkokchallenge.data.local.SharedPreferenceStorage
import com.example.bangkokchallenge.timeline.TimeLineActivity
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.callback.UnLinkResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException


/**
 * Created by choejun-yeong on 04/04/2020.
 */


class LoginActivity : AppCompatActivity(), LoginContract.View {

    /*로그인, 로그아웃 버튼*/
    private lateinit var kakaoLoginButton: Button
    private lateinit var kakaoLogoutButton:Button

    override lateinit var presenter: LoginContract.Presenter

    // 세션 콜백 구현
    private val sessionCallback: ISessionCallback = object : ISessionCallback {
        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response) {
                    val userSessionToken = Session.getCurrentSession().tokenInfo.accessToken

                    /*로그인 성공시 유저 토큰가지고 액티비티 전환*/
                    startActivity(Intent(applicationContext,TimeLineActivity::class.java))
                    if (BuildConfig.DEBUG) {
                        //toastMessage("[DEV] onSuccess() user token: $userSessionToken")
                    }
                }//onSuccess
                override fun onSessionClosed(errorResult: ErrorResult) {
                    // todo : 세션 응답 실패 처리
                }//closed
            })
        }//onSessionOpened

        override fun onSessionOpenFailed(exception: KakaoException) {
            Log.e("KAKAO_SESSION", "로그인 실패", exception)
        }
    }//callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences=this.getSharedPreferences("user_info",0)
        editor=sharedPreferences.edit()
        prefs=SharedPreferenceStorage(this@LoginActivity)

        initViewBinding()

        presenter = LoginPresenter(this)

    }

    override fun onResume() { //onCreate 호출되기 전
        super.onResume()
        presenter.start()
    }

    override fun onDestroy() { //완전히 종료될 때
        super.onDestroy()
        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        /*카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달*/
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {

            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initViewBinding() {
        kakaoLoginButton = findViewById(R.id.kakao_login_button)
        kakaoLogoutButton=findViewById(R.id.kakao_logout_button)

        kakaoLoginButton.setOnClickListener {
            presenter.requestLogin()
            //presenter.requestLogin(userSessionToken) // 서버로 UserSessiontoKen 을 송신.
        }

        kakaoLogoutButton.setOnClickListener {
            presenter.requestLogout()
        }
    }

    override fun login() {
        val session = Session.getCurrentSession()

        /*  ==  Session.getCurrentSession().addCallback(sessionCallback)*/
        session.addCallback(sessionCallback)
        session.open(AuthType.KAKAO_LOGIN_ALL, this);
    }

    override fun logout(){
        //로그아웃
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
                Log.i("KAKAO_API", "로그아웃 완료")

            }
        })
        //연결해제
        UserManagement.getInstance()
            .requestUnlink(object : UnLinkResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {
                    Log.e("KAKAO_API", "세션이 닫혀 있음: $errorResult")
                }

                override fun onFailure(errorResult: ErrorResult) {
                    Log.e("KAKAO_API", "연결 끊기 실패: $errorResult")
                }

                override fun onSuccess(result: Long) {
                    Log.i("KAKAO_API", "연결 끊기 성공. id: $result")
                }
            })
    }

    /*fun getHashKey(context: Context): String? { // 키 해시값 출력 코드
       try {
           if (Build.VERSION.SDK_INT >= 28) {
               val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
               val signatures = packageInfo.signingInfo.apkContentsSigners
               val md = MessageDigest.getInstance("SHA")
               for (signature in signatures) {
                   md.update(signature.toByteArray())
                   return String(Base64.encode(md.digest(), NO_WRAP))
               }
           } else {
               val packageInfo =
                   getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

               for (signature in packageInfo!!.signatures) {
                   try {
                       val md = MessageDigest.getInstance("SHA")
                       md.update(signature.toByteArray())
                       return Base64.encodeToString(md.digest(), Base64.NO_WRAP)
                   } catch (e: NoSuchAlgorithmException) {
                       // ERROR LOG
                   }
               }
           }
       } catch (e: PackageManager.NameNotFoundException) {
           e.printStackTrace()
       } catch (e: NoSuchAlgorithmException) {
           e.printStackTrace()
       }

       return null
   }*/
}