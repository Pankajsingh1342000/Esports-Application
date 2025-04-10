package com.example.esportgames.feature.auth.viewmodel

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.esportgames.MainActivity
import com.example.esportgames.feature.auth.data.User
import com.example.esportgames.util.SharedPrefManager
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit

class AuthViewmodel(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _otpSent = MutableLiveData<String?>()
    val otpSent: LiveData<String?> get() = _otpSent

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _userLoaded = MutableLiveData<User?>()
    val userLoaded: LiveData<User?> get() = _userLoaded

    fun sendOtp(phone: String, activity: Activity) {
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(phoneAuthCredential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.e("AuthViewModel", "OTP verification failed: ${e.message}")
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    _otpSent.postValue(verificationId)
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(verificationId: String, otp: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginSuccess.postValue(true)
                } else {
                    _loginSuccess.postValue(false)
                }
            }
    }

    fun saveUserToFirestore(context: Context,firstName: String, lastName: String, phone: String) {
        val user = User(firstName, lastName, phone)
        firestore.collection("users").document(phone).set(user)
            .addOnSuccessListener {
                _userLoaded.postValue(user)
                saveUserToPrefs(context, user)
                Log.d("AuthViewmodel", "User saved to Firestore: $user")
            }
            .addOnFailureListener {
                Log.e("AuthViewModel", "Firestore save failed: ${it.message}")
            }
    }

    fun loadUserFromFirestore(context: Context,phone: String) {
        firestore.collection("users").document(phone).get()
            .addOnSuccessListener { doc ->
                val user = doc.toObject(User::class.java)
                _userLoaded.postValue(user)
                saveUserToPrefs(context, user!!)
            }
    }

    fun saveUserToPrefs(context: Context, user: User) {
        SharedPrefManager(context).saveUser(user)
    }
}