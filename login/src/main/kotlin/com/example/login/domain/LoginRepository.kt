package com.example.login.domain

import com.example.login.data.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginRepository {
    fun loginByUser(user: String, pass: String): Flow<Response<LoginResponse>>
}