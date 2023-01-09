package com.example.login.data.repository

import com.example.core.createRetrofitBuilder
import com.example.login.data.service.LoginService
import com.example.login.domain.LoginRepository
import kotlinx.coroutines.flow.flow

class LoginRepositoryImp : LoginRepository {
    override fun loginByUser(user: String, pass: String) = flow {
        emit(
            createRetrofitBuilder(LoginService::class.java)
                .loginByUserNamePass(user)
        )
    }
}