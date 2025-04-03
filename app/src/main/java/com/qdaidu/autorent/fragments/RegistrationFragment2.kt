package com.qdaidu.autorent.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.RegistrationFragment1Binding
import com.qdaidu.autorent.databinding.RegistrationFragment2Binding
import com.qdaidu.autorent.helpers.PreferenceManagerNameData
import java.util.Calendar

class RegistrationFragment2: Fragment(R.layout.registration_fragment2) {

    private var _binding: RegistrationFragment2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = RegistrationFragment2Binding.bind(view)

        binding.button.setOnClickListener {

            var isValid = true
            var hasSecondName = false

            if (binding.editTextText.text.toString().trim().isEmpty()) {
                binding.editTextText.error = "Введите свою фамилию"
                isValid = false
            }
            if (binding.editTextText2.text.toString().trim().isEmpty()) {
                binding.editTextText2.error = "Введите свое имя"
                isValid = false
            }
            if (binding.editTextDate.text.toString().trim().isEmpty()) {
                binding.editTextDate.error = "Введите дату рождения"
                isValid = false
            }

            if (binding.toggleGroup.checkedButtonId == View.NO_ID){
                binding.textView6.error = "Пожалуйста, выберите пол"
                isValid = false
            }

            if (binding.editTextText3.text.toString().trim().isEmpty()) {
                hasSecondName = true
            }
            if (isValid){
                val preferenceManagerNameData = PreferenceManagerNameData(requireContext())
                preferenceManagerNameData.setName(binding.editTextText2.text.toString())
                preferenceManagerNameData.setSecondName(binding.editTextText3.text.toString())
                preferenceManagerNameData.setSurname(binding.editTextText.text.toString())
                preferenceManagerNameData.setSex(binding.toggleGroup.checkedButtonId)
                preferenceManagerNameData.setBirthDate(binding.editTextDate.text.toString())
                findNavController().navigate(R.id.action_registrationFragment2_to_registrationFragment3)
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment2_to_registrationFragment1)
        }
        binding.editTextDate.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, // Устанавливаем стиль спиннера
                { _, selectedYear, selectedMonth, selectedDay ->
                    binding.editTextDate.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year, month, day
            )

            // Принудительно устанавливаем режим "спиннера"
            try {
                datePickerDialog.datePicker.calendarViewShown = false // Отключаем календарь
                datePickerDialog.datePicker.spinnersShown = true // Включаем спиннер
            } catch (e: Exception) {
                e.printStackTrace()
            }

            datePickerDialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}