package com.example.sample_feature_module.di

import android.app.Application
import android.content.Context
import com.example.sample_feature_module.AssignmentApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConText(): Context {
        return AssignmentApplication().applicationContext
    }
}
