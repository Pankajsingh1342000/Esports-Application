package com.example.esportgames.feature.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.esportgames.MainActivity
import com.example.esportgames.R
import com.example.esportgames.databinding.FragmentProfileBinding
import com.example.esportgames.databinding.FragmentVerifyOtpBinding
import com.example.esportgames.util.SharedPrefManager

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPrefManager = SharedPrefManager(requireContext())

        loadUserInfo()

        binding.btnLogout.setOnClickListener {
            sharedPrefManager.logout()
            (requireActivity() as MainActivity).showAuthContainer()
        }
    }

    private fun loadUserInfo() {
        val user = sharedPrefManager.getUser()
        if (user != null){
            binding.firstNameText.text = user.firstName
            binding.lastNameText.text = user.lastName
            binding.phoneNumberText.text = user.phoneNumber
        }
        else {
            Toast.makeText(requireContext(), "User data not found", Toast.LENGTH_SHORT).show()
        }
    }

}