package com.mdr.petoffers.view.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mdr.petoffers.models.AlertModel
import com.mdr.petoffers.repository.AlertRepository
import com.mdr.petoffers.repository.UserRepository

class MainViewModel : ViewModel() {

    val alertRepository = AlertRepository()

    val userRepository = UserRepository()

    val alerts : MutableLiveData<List<AlertModel>> = MutableLiveData()
    val mensagemErro : MutableLiveData<String> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isAllowed: MutableLiveData<Boolean> = MutableLiveData()


    fun buscarTodos () {
        isLoading.value = true
        alertRepository.buscarTodos(
            onComplete = {
                isLoading.value = false
                alerts.value = it

            },
            onError = {
                isLoading.value = false
                mensagemErro.value = it?.message
            }
        )
    }

    fun login (id:String) {
        isLoading.value = true
        userRepository.login(id,
            onComplete = {
                isLoading.value = false
                isAllowed.value = it.allowed

            },
            onError = {
                isLoading.value = false
                mensagemErro.value = it?.message
            }
        )
    }

}