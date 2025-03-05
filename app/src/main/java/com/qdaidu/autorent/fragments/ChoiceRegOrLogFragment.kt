package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.ChoiceRegOrLogBinding


class ChoiceRegOrLogFragment : Fragment(R.layout.choice_reg_or_log) {
    private var _binding: ChoiceRegOrLogBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = ChoiceRegOrLogBinding.bind(view)

        binding.buttonChoiceLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_choiceRegOrLogFragment_to_logInFragment)
        }
        binding.buttonChoiceSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_choiceRegOrLogFragment_to_registrationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
