package com.example.mencak.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mencak.model.Injection
import com.example.mencak.ui.register.RegisterViewModel

class ViewModelFactory(private val pref: Context) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                MainViewModel(Injection.provideRepository(pref)) as T
//            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.userRepository(pref)) as T
            }
//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
//                LoginViewModel(Injection.userRepository(pref)) as T
//            }
//            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
//                MapsViewModel(Injection.userRepository(pref)) as T
//            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}