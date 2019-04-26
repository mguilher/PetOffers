package com.mdr.petoffers.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor

class RetrofitAPI<T> {

    fun getClient(c: Class<T>) : T {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient())
            .build()

        return retrofit.create(c)
    }
}

fun getOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

fun getAlertApi(): AlertApi {
    return RetrofitAPI<AlertApi>().getClient(AlertApi::class.java)
}

fun getUserApi(): UserApi {
    return RetrofitAPI<UserApi>().getClient(UserApi::class.java)
}