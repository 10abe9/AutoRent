package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.RegistrationFragment1Binding
import com.qdaidu.autorent.databinding.RegistrationFragment4Binding

class RegistrationFragment4: Fragment(R.layout.registration_fragment4) {

    private var _binding: RegistrationFragment4Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = RegistrationFragment4Binding.bind(view)

        binding.button.setOnClickListener {
            //findNavController().navigate(R.id.action_registrationFragment1_to_registrationFragment2)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}