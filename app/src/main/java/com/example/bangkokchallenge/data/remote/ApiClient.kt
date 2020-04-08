package com.example.bangkokchallenge.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by choejun-yeong on 04/04/2020.
 */

class ApiClient {
    companion object {
        const val BASE_URL = "" // 서버 Url
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}