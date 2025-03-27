package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.RegistrationFragment1Binding
import com.qdaidu.autorent.databinding.RegistrationFragment2Binding

class RegistrationFragment2: Fragment(R.layout.registration_fragment2) {

    private var _binding: RegistrationFragment2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = RegistrationFragment2Binding.bind(view)

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment2_to_registrationFragment3)
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment2_to_registrationFragment1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}