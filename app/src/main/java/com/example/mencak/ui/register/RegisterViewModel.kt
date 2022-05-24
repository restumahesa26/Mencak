package com.example.mencak.ui.register

import androidx.lifecycle.ViewModel
import com.example.mencak.model.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun saveUser(name: String, email: String, password: String) = userRepository.saveUser(name, email, password)
}