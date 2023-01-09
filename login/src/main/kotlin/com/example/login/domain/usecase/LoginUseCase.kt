package com.example.login.domain.usecase

import com.example.login.data.model.LoginResponse
import com.example.login.domain.LoginInfModel
import com.example.login.domain.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LoginUseCase {
    suspend fun execute(user: String, pass: String): Flow<LoginInfModel>
}

class LoginUseCaseImpl(private val loginRepository: LoginRepository) : LoginUseCase {

    override suspend fun execute(user: String, pass: String) = flow {
        loginRepository.loginByUser(user, pass)
            .collect {
                if (it.isSuccessful) {
                    emit(it.body()?.let { it1 -> mapperToModel(it1) }?: LoginInfModel())
                } else {
                    error("error loginByUser")
                }
            }
    }

    private fun mapperToModel(loginResponse: LoginResponse) = LoginInfModel(
        loginResponse.age,
        loginResponse.count,
        loginResponse.name
    )

}