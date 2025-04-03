package com.qdaidu.autorent.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.Onboarding1Binding
import com.qdaidu.autorent.databinding.RegistrationFragment1Binding
import com.qdaidu.autorent.helpers.PreferenceManagerData
import java.security.MessageDigest

class RegistrationFragment1: Fragment(R.layout.registration_fragment1) {
    private var _binding: RegistrationFragment1Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = RegistrationFragment1Binding.bind(view)

        binding.button.setOnClickListener {
            var isValid = true

            if (binding.editTextText.text.toString().trim().isEmpty()) {
                binding.editTextText.error = "Введите электронную почту"
                isValid = false
            }
            if (!stringIsEmail(binding.editTextText.text.toString().trim())){
                binding.editTextText.error = "Введите валидную электронную почту"
                isValid = false
            }

            if (binding.editTextText2.text.toString().trim().isEmpty()) {
                binding.editTextText2.error = "Введите пароль"
                isValid = false
            }else if (binding.editTextText2.text.toString().length < 5){
                binding.editTextText2.error = "Пароль должен содержать не менее 5 символов"
                isValid = false

            }
            if (binding.editTextText3.text.toString().trim() != binding.editTextText2.text.toString().trim()) {
                binding.editTextText3.error = "Пароли не совпадают"
                binding.editTextText2.error = "Пароли не совпадают"
                isValid = false
            }

            if (!binding.checkBox.isChecked){
                binding.textView13.error = "Примите условия обслужвания чтобы продолжить работу в приложении!"
                isValid = false
            }

            if (isValid) {
                // Все обязательные поля заполнены. Можно продолжать регистрацию.
                //TODO: ОТПРАВИТЬ ДАННЫЕ В ПРЕФЕРЕНС ИЛИ КУДА ЕЩЕ
                val preferenceManager = PreferenceManagerData(requireContext())
                preferenceManager.setLogin(binding.editTextText.text.toString())
                preferenceManager.setPasswordHash(binding.editTextText2.text.toString().md5())
                findNavController().navigate(R.id.action_registrationFragment1_to_registrationFragment2)
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment1_to_choiceRegOrLogFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun stringIsEmail(str:String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()
    }
    @OptIn(ExperimentalStdlibApi::class)
    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(this.toByteArray())
        return digest.toHexString()
    }
}