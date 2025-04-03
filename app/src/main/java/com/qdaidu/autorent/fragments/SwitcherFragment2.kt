package com.qdaidu.autorent.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.SwitcherFragment2Binding
import com.qdaidu.autorent.helpers.PreferenceManagerData
import com.qdaidu.autorent.helpers.PreferenceManagerNameData
import com.qdaidu.autorent.helpers.PreferenceManagerPhoto

class SwitcherFragment2 : Fragment(R.layout.switcher_fragment_2) {
    private val activeSwitch = 2
    private var _binding: SwitcherFragment2Binding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        val preferenceManagerData = PreferenceManagerData(requireContext())
        val preferenceManagerNameData = PreferenceManagerNameData(requireContext())
        val preferenceManagerPhoto = PreferenceManagerPhoto(requireContext())
        _binding = SwitcherFragment2Binding.bind(view)

        val email = preferenceManagerData.getLogin().toString()
        val name = preferenceManagerNameData.getName().toString() + " " + preferenceManagerNameData.getSurname().toString()
        val dateBirth = preferenceManagerNameData.getBirthDate().toString()
        val google = "Not added"
        val sex = preferenceManagerNameData.getSex()

        binding.textView22.setText(email)
        binding.textView16.setText(name)
        binding.textView20.setText(dateBirth)
        binding.textView28.setText("Not allowed")
        if (preferenceManagerPhoto.getAvatarPath() != null){
            binding.imageView9.setImageBitmap(loadImageFromInternalStorage(preferenceManagerPhoto.getAvatarPath().toString()))
        }

        if (sex == 1){
            binding.textView26.setText("Male")

        }else{
            binding.textView26.setText("Female")
        }

        binding.imageView6.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment2_to_switcherFragment1)
        }

        binding.imageView8.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment2_to_switcherFragment3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Очищаем биндинг, чтобы избежать утечек памяти
        _binding = null
    }

    private fun loadImageFromInternalStorage(filePath: String): Bitmap? {
        return BitmapFactory.decodeFile(filePath)
    }

}
