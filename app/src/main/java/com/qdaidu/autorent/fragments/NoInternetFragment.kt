package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.NoInternetBinding

class NoInternetFragment: Fragment(R.layout.no_internet) {

        private var _binding: NoInternetBinding? = null
        private val binding get() = _binding!!

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            // Инициализируем binding, привязываясь к уже созданному view
            _binding = NoInternetBinding.bind(view)

            binding.tryAgainButton.setOnClickListener {
                findNavController().navigate(R.id.action_noInternetFragment_to_mainFragment)
            }
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
