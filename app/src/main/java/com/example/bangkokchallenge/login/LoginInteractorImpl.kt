package com.example.bangkokchallenge.login


import android.util.Log
import com.example.bangkokchallenge.data.remote.ApiClient
import com.example.bangkokchallenge.data.remote.ApiService
import com.example.bangkokchallenge.model.AccountDTO
import com.example.bangkokchallenge.model.request.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractorImpl :LoginContract.LoginInteractor{
    override fun sendAccessToken(token : String,onFinishedListener: LoginContract.LoginInteractor.OnFinishedListener) {

            //Log.d("@@@",token)

        val service = ApiClient.getClient().create(ApiService::class.java)
        val call = service.login(LoginRequest(token)) //

        call.enqueue(object : Callback<AccountDTO> {

            override fun onResponse(call: Call<AccountDTO>, response: Response<AccountDTO>?) {
                val accountResponse = response?.body()

                accountResponse?.let {
                    Log.d("@@@lele",""+response?.body())
                    onFinishedListener.onSendTokenSuccess(accountResponse)
                }
            }

            override fun onFailure(call: Call<AccountDTO>, t: Throwable) {
                ///onFinishedListener.onFailure(t)
                Log.d("@@rere",t.message)
            }

        })

    }
}