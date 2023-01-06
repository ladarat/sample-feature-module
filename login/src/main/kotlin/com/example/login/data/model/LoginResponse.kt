package com.example.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("age")
    val age: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("name")
    val name: String
)
