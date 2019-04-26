package com.mdr.petoffers.repository

import com.mdr.petoffers.api.getAlertApi
import com.mdr.petoffers.models.AlertModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlertRepository {

    fun buscarTodos(
        onComplete:(List<AlertModel>?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {

        getAlertApi()
            .getAlerts()
            .enqueue(object : Callback<List<AlertModel>>{
                override fun onFailure(call: Call<List<AlertModel>>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<List<AlertModel>>, response: Response<List<AlertModel>>) {
                    if(response.isSuccessful) {
                        onComplete(response.body())
                    } else {
                        onError(Throwable("Erro ao buscar os dados"))
                    }
                }
            })

    }



    fun salvar(alert: AlertModel,
               onComplete: (AlertModel) -> Unit,
               onError: (Throwable?) -> Unit) {
        getAlertApi()
            .salvar(alert)
            .enqueue(object : Callback<AlertModel>{
                override fun onFailure(call: Call<AlertModel>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<AlertModel>, response: Response<AlertModel>) {
                    onComplete(response.body()!!)
                }
            })
    }





}