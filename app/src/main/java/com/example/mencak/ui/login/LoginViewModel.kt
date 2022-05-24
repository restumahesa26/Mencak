package com.example.mencak.ui.login

import androidx.lifecycle.ViewModel
import com.example.mencak.model.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun login(email: String, password: String) = userRepository.login(email, password)
}