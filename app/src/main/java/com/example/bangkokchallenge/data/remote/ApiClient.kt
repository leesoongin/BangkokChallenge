package com.example.bangkokchallenge.data.remote

import com.example.bangkokchallenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by choejun-yeong on 04/04/2020.
 */

object ApiClient {
    const val BASE_URL = BuildConfig.SERVER_URL // 서버 Url
    private var retrofit: Retrofit? = null
    private var okhttpLogging : OkHttpClient? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpLoggingInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }


    fun getOkHttpLoggingInterceptor() : OkHttpClient{
        if(okhttpLogging == null){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okhttpLogging = OkHttpClient.Builder()
                .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
        return okhttpLogging as OkHttpClient


    }




}