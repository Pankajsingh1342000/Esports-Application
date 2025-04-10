package com.example.esportgames.util

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.esportgames.feature.auth.data.User

class SharedPrefManager(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _isLoggedIn = MutableLiveData<Boolean>(prefs.getBoolean(KEY_IS_LOGGED_IN, false))
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    companion object {
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }

    fun saveUser(user: User) {
        prefs.edit()
            .putString("firstName", user.firstName)
            .putString("lastName", user.lastName)
            .putString("phoneNumber", user.phoneNumber)
            .putBoolean(KEY_IS_LOGGED_IN, true)
//            .putBoolean("isLoggedIn", true)
            .apply()
        _isLoggedIn.postValue(true)
    }

    fun getUser(): User? {
        val firstName = prefs.getString("firstName", null)
        val lastName = prefs.getString("lastName", null)
        val phoneNumber = prefs.getString("phoneNumber", null)

        return if (!firstName.isNullOrEmpty() && !phoneNumber.isNullOrEmpty()) {
            User(firstName, lastName ?: "", phoneNumber)
        } else {
            null
        }
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("isLoggedIn", false)
    }

    fun logout() {
        prefs.edit().clear().apply()
        _isLoggedIn.postValue(false)
    }
}