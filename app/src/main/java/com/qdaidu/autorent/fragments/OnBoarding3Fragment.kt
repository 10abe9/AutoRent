package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.MainFragmentBinding
import com.qdaidu.autorent.databinding.Onboarding3Binding
import com.qdaidu.autorent.helpers.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OnBoarding3Fragment : Fragment(R.layout.onboarding_3) {
    private var _binding: Onboarding3Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = Onboarding3Binding.bind(view)

        binding.buttonGo.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding3Fragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
