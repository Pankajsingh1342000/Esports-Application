package com.example.esportgames.feature.auth.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.esportgames.MainActivity
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentSignUpBinding
import com.example.esportgames.feature.auth.viewmodel.AuthViewModelFactory
import com.example.esportgames.feature.auth.viewmodel.AuthViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: AuthViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        handleBackPress()
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        val prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val factory = AuthViewModelFactory(auth, firestore, prefs)
        viewModel = ViewModelProvider(this, factory)[AuthViewmodel::class.java]

        binding.continueButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            viewModel.sendOtp(phone, requireActivity())

            viewModel.otpSent.observe(viewLifecycleOwner) { verificationId ->
                val action = SignUpFragmentDirections.actionSignUpFragmentToVerifyOtpFragment(
                    verificationId!!, true, firstName, lastName, phone
                )
                findNavController().navigate(action)
            }
        }

    }
    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navController.popBackStack()
                (activity as? MainActivity)

            }
        })
    }
}