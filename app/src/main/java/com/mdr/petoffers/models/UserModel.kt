package com.mdr.petoffers.models

data class UserModel(
    val id: String? = null,
    val email: String,
    val pwd: String,
    val name: String
)