package com.qdaidu.autorent.helpers


import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val isFirstInit = "isFirstInit"

    }

    fun getIsFirstInit():Boolean{
        return preferences.getBoolean(isFirstInit, true)
    }

    fun setIsFirstInit(){
        val editor = preferences.edit()
        editor.putBoolean(isFirstInit, false)
        editor.apply()
    }
}
