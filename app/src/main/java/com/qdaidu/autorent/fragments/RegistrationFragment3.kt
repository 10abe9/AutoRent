package com.qdaidu.autorent.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.RegistrationFragment3Binding
import com.qdaidu.autorent.helpers.PreferenceManagerData
import com.qdaidu.autorent.helpers.PreferenceManagerPhoto
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Calendar

class RegistrationFragment3 : Fragment(R.layout.registration_fragment3) {

    private var _binding: RegistrationFragment3Binding? = null
    private val binding get() = _binding!!

    // Определяем разные request-коды для каждого выбора фото
    private val PICK_IMAGE_REQUEST = 1
    private val PICK_LICENSE_IMAGE_REQUEST = 2
    private val PICK_PASSPORT_IMAGE_REQUEST = 3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = RegistrationFragment3Binding.bind(view)

        binding.button.setOnClickListener {
            var isValid = true

            // Проверка номера
            if (binding.editTextText.text.toString().length < 10) {
                binding.editTextText.error = "Введите валидный номер (10 символов)"
                isValid = false
            }

            // Проверка даты
            if (binding.editTextDate2.text.toString().trim().isEmpty()) {
                binding.editTextDate2.error = "Введите дату получения прав"
                isValid = false
            }

            // Проверка загруженных изображений
            val preferenceManagerPhoto = PreferenceManagerPhoto(requireContext())
            val preferenceManagerData = PreferenceManagerData(requireContext())
            val avatarPath = preferenceManagerPhoto.getAvatarPath()
            val licensePath = preferenceManagerPhoto.getLicensePath()


            if (avatarPath != null) {
                if (licensePath != null) {
                        if (avatarPath.isEmpty() || licensePath.isEmpty()) {
                            binding.textView12.error = "Загрузите все изображения!"
                            isValid = false
                    }
                }
            }

            // Переход на следующий шаг, если всё ок
            if (isValid) {
                preferenceManagerData.setDateOfIssue(binding.editTextDate2.text.toString().trim())
                preferenceManagerData.setLicenseNumber(binding.editTextText.text.toString())
                findNavController().navigate(R.id.action_registrationFragment3_to_registrationFragment4)
            }
        }


        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment3_to_registrationFragment2)
        }

        binding.editTextDate2.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, // стиль спиннера
                { _, selectedYear, selectedMonth, selectedDay ->
                    binding.editTextDate2.setText("$selectedDay/${selectedMonth + 1}/$selectedYear")
                },
                year, month, day
            )

            try {
                datePickerDialog.datePicker.calendarViewShown = false // отключаем календарь
                datePickerDialog.datePicker.spinnersShown = true     // включаем спиннер
            } catch (e: Exception) {
                e.printStackTrace()
            }
            datePickerDialog.show()
        }

        // Нажатие на imageView3 – выбираем фото и устанавливаем его в imageView2
        binding.imageView3.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Нажатие на imageView4 – выбираем фото и устанавливаем его в imageView4, а затем сохраняем
        binding.imageView4.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_LICENSE_IMAGE_REQUEST)
        }

        // Нажатие на imageView5 – выбираем фото и устанавливаем его в imageView5, а затем сохраняем
        binding.imageView5.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_PASSPORT_IMAGE_REQUEST)
        }
    }

    // Обработка результата выбора изображения
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    // Выбранное фото отображается в imageView2
                    binding.imageView2.setImageURI(imageUri)
                    imageUri?.let { saveImageAndStorePath(it, "avatar_photo")}
                }
                PICK_LICENSE_IMAGE_REQUEST -> {
                    // Выбранное фото отображается в imageView4
                    binding.imageView4.setImageURI(imageUri)
                    // Сохраняем фото во внутреннее хранилище и сохраняем путь в SharedPreferences
                    imageUri?.let { saveImageAndStorePath(it, "license_photo") }
                }
                PICK_PASSPORT_IMAGE_REQUEST -> {
                    // Выбранное фото отображается в imageView5
                    binding.imageView5.setImageURI(imageUri)
                    // Сохраняем фото во внутреннее хранилище и сохраняем путь в SharedPreferences
                    imageUri?.let { saveImageAndStorePath(it, "passport_photo") }
                }
            }
        }
    }

    // Функция для сохранения изображения из Uri во внутреннее хранилище
    private fun saveImageAndStorePath(uri: Uri, prefKey: String) {
        try {
            // Получаем Bitmap из Uri
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream?.close()

            // Сохраняем Bitmap и получаем путь к файлу
            val filePath = saveImageToInternalStorage(bitmap)
            // Сохраняем путь в SharedPreferences
            val preferenceManagerPhoto = PreferenceManagerPhoto(requireContext())
            if (prefKey == "avatar_photo"){
                preferenceManagerPhoto.setAvatarPath(filePath)
            }
            else if (prefKey =="license_photo"){
                preferenceManagerPhoto.setLicensePath(filePath)
            }
            else if (prefKey =="passport_photo"){
                preferenceManagerPhoto.setPassportPath(filePath)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Функция для сохранения Bitmap во внутреннее хранилище и возвращения пути к файлу
    private fun saveImageToInternalStorage(bitmap: Bitmap): String {
        val filename = "photo_${System.currentTimeMillis()}.jpg"
        val file = File(requireContext().filesDir, filename)
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file.absolutePath
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
