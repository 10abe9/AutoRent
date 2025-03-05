package com.qdaidu.autorent.fragments


import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.qdaidu.autorent.R
import com.qdaidu.autorent.databinding.MainFragmentBinding
import com.qdaidu.autorent.helpers.PreferenceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.main_fragment) {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferenceManager = context?.let { PreferenceManager(it) }
        val isFirstInit = preferenceManager?.getIsFirstInit()
        preferenceManager?.setIsFirstInit()

        // Запускаем корутину, которая через 1 секунду проверит наличие интернета
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000L) // задержка в 1 секунду

            if (isFirstInit == true){
                findNavController().navigate(R.id.action_mainFragment_to_onBoarding1Fragment)
            }

            else if (isInternetAvailable(requireContext())) {
                // Если интернет есть, переходим на экран OnlineFragment
                findNavController().navigate(R.id.action_mainFragment_to_choiceRegOrLogFragment)
            } else {
                // Если интернета нет, переходим на экран OfflineFragment
                findNavController().navigate(R.id.action_mainFragment_to_noInternetFragment)
            }
        }
    }

    // Функция проверки подключения к интернету
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

