package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.LogInBinding
import com.qdaidu.autorent.helpers.PreferenceManagerData
import java.security.MessageDigest
import kotlin.math.log

class LogInFragment: Fragment(R.layout.log_in) {
    private var _binding: LogInBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isValid = true
        val preferenceManagerData = PreferenceManagerData(requireContext())
        val email = preferenceManagerData.getLogin()
        val passHash = preferenceManagerData.getPasswordHash()

        // Инициализируем binding, привязываясь к уже созданному view
        _binding = LogInBinding.bind(view)

        binding.buttonLogIn.setOnClickListener {
            Log.d(binding.editTextInputEMail.text.toString() , email.toString() )
            Log.d(binding.editTextInputEMail.text.toString().md5() , passHash.toString() )
            if (binding.editTextInputEMail.text.toString() == email && binding.editTextPassword.text.toString().md5() == passHash){
                //TODO go next\
                findNavController().navigate(R.id.action_logInFragment_to_switcherFragment3)
            }
        }
        binding.textViewForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registrationFragment1)
        }
        binding.textViewToSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_logInFragment_to_registrationFragment1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(this.toByteArray())
        return digest.toHexString()
    }
}