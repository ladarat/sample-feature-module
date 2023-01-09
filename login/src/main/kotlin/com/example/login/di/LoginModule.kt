package com.example.login.di

import com.example.login.data.repository.LoginRepositoryImp
import com.example.login.data.service.LoginService
import com.example.login.domain.LoginRepository
import com.example.login.domain.usecase.LoginUseCase
import com.example.login.domain.usecase.LoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideCoinRankingApi(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCaseImpl(loginRepository)
    }

    @Provides
    fun provideLoginRepository(loginService: LoginService): LoginRepository {
        return LoginRepositoryImp(loginService)
    }

}