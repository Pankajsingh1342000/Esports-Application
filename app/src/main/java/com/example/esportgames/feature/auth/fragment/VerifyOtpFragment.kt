package com.example.esportgames.feature.auth.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.esportgames.MainActivity
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentVerifyOtpBinding
import com.example.esportgames.feature.auth.viewmodel.AuthViewModelFactory
import com.example.esportgames.feature.auth.viewmodel.AuthViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VerifyOtpFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentVerifyOtpBinding
    private lateinit var viewModel: AuthViewmodel
    private val args: VerifyOtpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVerifyOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        handleBackPress()
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()
        val prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val factory = AuthViewModelFactory(auth, firestore, prefs)
        viewModel = ViewModelProvider(this, factory)[AuthViewmodel::class.java]

        binding.verifyOtpButton.setOnClickListener {
            val otp = binding.otpEditText.text.toString()
            viewModel.verifyOtp(args.verificationId, otp)
        }

        viewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                if (args.isSignup) {
                    viewModel.saveUserToFirestore(requireContext(),args.firstName, args.lastName, args.phoneNumber)
                    (requireActivity() as MainActivity).showMainContainer()
                } else {
                    viewModel.loadUserFromFirestore(requireContext(), args.phoneNumber)
                    (requireActivity() as MainActivity).showMainContainer()
                }
            }
        }

        viewModel.userLoaded.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                viewModel.saveUserToPrefs(requireContext(), user)
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