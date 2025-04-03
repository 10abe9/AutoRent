package com.qdaidu.autorent.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.database.SuperbaseConnection
import com.qdaidu.autorent.databinding.RegistrationFragment1Binding
import com.qdaidu.autorent.databinding.RegistrationFragment4Binding
import com.qdaidu.autorent.helpers.PreferenceManagerData
import com.qdaidu.autorent.helpers.PreferenceManagerNameData
import com.qdaidu.autorent.helpers.PreferenceManagerPhoto

class RegistrationFragment4: Fragment(R.layout.registration_fragment4) {

    private var _binding: RegistrationFragment4Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = RegistrationFragment4Binding.bind(view)

        binding.button.setOnClickListener {
            //TODO: Send data to server
            //if OK
            val p1 = PreferenceManagerData(requireContext())
            val p2 = PreferenceManagerPhoto(requireContext())
            val p3 = PreferenceManagerNameData(requireContext())
            Log.d("Values", p1.getLogin().toString())
            Log.d("Values", p1.getPasswordHash().toString())
            Log.d("Values", p1.getLicenseNumber().toString())
            Log.d("Values", p1.getDateOfIssue().toString())
            Log.d("Values", p2.getAvatarPath().toString())
            Log.d("Values", p2.getLicensePath().toString())
            Log.d("Values", p2.getpassportPath().toString())
            Log.d("Values", p3.getName().toString())
            Log.d("Values", p3.getSurname().toString())
            Log.d("Values", p3.getBirthDate().toString())
            Log.d("Values", p3.getSecondName().toString())
            Log.d("Values", p3.getSex().toString()) // 1 = male


            findNavController().navigate(R.id.action_registrationFragment4_to_logInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}