package com.example.esportgames.feature.auth.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModelFactory(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewmodel::class.java)) {
            return AuthViewmodel(auth, firestore, sharedPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}