package com.mdr.petoffers.api

import com.mdr.petoffers.models.AlertModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlertApi {

    @GET("/alerts/listalerts")
    fun getAlerts() : Call<List<AlertModel>>

    @POST("/alerts")
    fun salvar(@Body alert: AlertModel): Call<AlertModel>

}