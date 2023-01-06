package com.example.login.data.repository

import com.example.login.data.model.LoginResponse
import com.example.login.data.service.LoginService
import com.example.login.domain.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class LoginRepositoryImp(
    private val loginService: LoginService
) : LoginRepository {
    override fun loginByUser(user: String, pass: String) = flow<Response<LoginResponse>> {
        loginService.loginByUserNamePass(user, pass)
    }
}