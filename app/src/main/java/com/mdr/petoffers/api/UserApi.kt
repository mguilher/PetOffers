package com.mdr.petoffers.api

import com.mdr.petoffers.models.SessionModel
import com.mdr.petoffers.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("/user/")
    fun getAlerts() : Call<List<UserModel>>

    @POST("/user")
    fun salvar(@Body user: UserModel): Call<UserModel>

    @POST("/login")
    fun login(@Body user: UserModel): Call<SessionModel>

    @GET("/sessions/{id}")
    fun getSession(@Path("id")id:String) : Call<SessionModel>
}