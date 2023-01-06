package com.example.login.data.service

import com.example.login.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET
    suspend fun loginByUserNamePass(
        @Query("name") name: String,
        @Query("pass") pass: String
    ): Response<LoginResponse>
}