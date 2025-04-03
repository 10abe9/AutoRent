package com.qdaidu.autorent.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.SwitcherFragment3Binding
import com.qdaidu.autorent.helpers.PreferenceManagerPhoto

class SwitcherFragment3 : Fragment(R.layout.switcher_fragment_3) {

    private val activeSwitch = 3

    private var _binding: SwitcherFragment3Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализируем binding, привязываясь к уже созданному view
        _binding = SwitcherFragment3Binding.bind(view)
        val preferenceManagerPhoto = PreferenceManagerPhoto(requireContext())
        if (preferenceManagerPhoto.getAvatarPath() != null){
            binding.imageView9.setImageBitmap(loadImageFromInternalStorage(preferenceManagerPhoto.getAvatarPath().toString()))
        }

        binding.imageView8.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment3_to_switcherFragment1)
        }

        binding.imageView7.setOnClickListener {
            findNavController().navigate(R.id.action_switcherFragment3_to_switcherFragment2)
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
