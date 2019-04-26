package com.mdr.petoffers.api

import com.mdr.petoffers.models.PetModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PetAPI {

    @GET("/pet/listpet")
    fun getNotas() : Call<List<PetModel>>

    @POST("/pet")
    fun salvar(@Body pet: PetModel): Call<PetModel>

}