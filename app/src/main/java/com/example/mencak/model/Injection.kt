package com.example.mencak.model

import android.content.Context
import com.example.mencak.model.api.ApiConfig
import com.example.mencak.model.repository.UserRepository

object Injection {
//    fun provideRepository(context: Context): StoryRepository {
//        val apiService = ApiConfig.getApiService()
//        return StoryRepository(apiService, context)
//    }

    fun userRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService, context)
    }
}