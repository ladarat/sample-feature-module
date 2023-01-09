package com.example.login.data.repository

import com.example.core.createRetrofitBuilder
import com.example.login.data.model.LoginResponse
import com.example.login.data.service.LoginService
import com.example.login.domain.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class LoginRepositoryImp: LoginRepository {
    override fun loginByUser(user: String, pass: String) = flow<Response<LoginResponse>> {
        createRetrofitBuilder(LoginService::class.java)
        .loginByUserNamePass(user)
    }
}