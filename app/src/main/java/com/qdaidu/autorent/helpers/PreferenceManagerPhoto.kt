package com.qdaidu.autorent.helpers


import android.content.Context
import android.content.SharedPreferences
import java.nio.file.Path

class PreferenceManagerPhoto(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val avatarPath = "avatarPath"
        private const val licensePath = "licensePath"
        private const val passportPath = "passportPath"


    }
    fun getAvatarPath(): String? {
        return preferences.getString(avatarPath, null)
    }

    fun setAvatarPath(photoPath: String){
        val editor = preferences.edit()
        editor.putString(avatarPath, photoPath)
        editor.apply()
    }
    fun getLicensePath(): String? {
        return preferences.getString(licensePath, null)
    }

    fun setLicensePath(photoPath: String){
        val editor = preferences.edit()
        editor.putString(licensePath, photoPath)
        editor.apply()
    }
    fun getpassportPath(): String? {
        return preferences.getString(passportPath, null)
    }

    fun setPassportPath(photoPath: String){
        val editor = preferences.edit()
        editor.putString(passportPath, photoPath)
        editor.apply()
    }
}
