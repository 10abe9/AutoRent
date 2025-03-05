package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.MainFragmentBinding
import com.qdaidu.autorent.databinding.Onboarding2Binding
import com.qdaidu.autorent.helpers.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OnBoarding2Fragment : Fragment(R.layout.onboarding_2) {
    private var _binding: Onboarding2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = Onboarding2Binding.bind(view)

        binding.buttonNext2.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding2Fragment_to_onBoarding3Fragment)
        }
        binding.textViewSkip2.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding2Fragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
