package com.mdr.petoffers.repository

import com.mdr.petoffers.api.getUserApi
import com.mdr.petoffers.models.SessionModel
import com.mdr.petoffers.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun salvar(user: UserModel,
               onComplete: (UserModel) -> Unit,
               onError: (Throwable?) -> Unit) {
        getUserApi()
            .salvar(user)
            .enqueue(object : Callback<UserModel>{
                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    onComplete(response.body()!!)
                }
            })
    }


    fun login(user: UserModel,
               onComplete: (SessionModel) -> Unit,
               onError: (Throwable?) -> Unit) {
        getUserApi()
            .login(user)
            .enqueue(object : Callback<SessionModel>{
                override fun onFailure(call: Call<SessionModel>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<SessionModel>, response: Response<SessionModel>) {
                    onComplete(response.body()!!)
                }
            })
    }

    fun login(id: String,
              onComplete: (SessionModel) -> Unit,
              onError: (Throwable?) -> Unit) {
        getUserApi()
            .getSession(id)
            .enqueue(object : Callback<SessionModel>{
                override fun onFailure(call: Call<SessionModel>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(call: Call<SessionModel>, response: Response<SessionModel>) {
                    onComplete(response.body()!!)
                }
            })
    }
}