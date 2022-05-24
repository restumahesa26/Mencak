package com.example.mencak.model

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {
    companion object {
        const val NAME = "NAME"
        const val USER_ID = "USER_ID"
        const val TOKEN = "TOKEN"
        private const val PREFS_NAME = "user_preferences"
    }

    private var prefs: SharedPreferences? =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveAuthToken(key: String, value: String) {
        val editor = prefs?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs?.getString(TOKEN, null)
    }
}