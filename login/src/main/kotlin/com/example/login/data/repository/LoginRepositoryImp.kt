package com.example.login.data.repository

import com.example.login.data.service.LoginService
import com.example.login.domain.LoginRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val loginService: LoginService) :
    LoginRepository {
    override fun loginByUser(user: String, pass: String) = flow {
        emit(
            loginService.loginByUserNamePass(user)
        )
    }
}