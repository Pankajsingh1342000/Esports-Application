package com.example.esportgames.feature.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.esportgames.MainActivity
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignup.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToSignUpFragment()
            navController.navigate(action)

        }
        binding.btnLogin.setOnClickListener {
            val action = AuthFragmentDirections.actionAuthFragmentToLogInFragment()
            navController.navigate(action)
        }

    }

}