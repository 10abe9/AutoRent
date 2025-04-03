package com.qdaidu.autorent.helpers


import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferenceManagerData(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val login = "login"
        private const val passwordHash = "passwordHash"
        private const val licenseNumber = "licenseNumber"
        private const val dateOfIssue = "dateOfIssue"


    }
    fun getLogin(): String? {
        return preferences.getString(login, null)
    }

    fun setLogin(loginStr: String){
        val editor = preferences.edit()
        editor.putString(login, loginStr)
        editor.apply()
    }

    fun getPasswordHash(): String? {
        return preferences.getString(passwordHash, null)
    }

    fun setPasswordHash(photoPath: String){
        val editor = preferences.edit()
        editor.putString(passwordHash, photoPath)
        editor.apply()
    }
    fun getLicenseNumber(): String? {
        return preferences.getString(licenseNumber, null)
    }

    fun setLicenseNumber(licanseNumberStr: String){
        val editor = preferences.edit()
        editor.putString(licenseNumber, licanseNumberStr)
        editor.apply()
    }
    fun getDateOfIssue(): String? {
        return preferences.getString(dateOfIssue, null)
    }

    fun setDateOfIssue(dateOfIssueStr: String){
        val editor = preferences.edit()
        editor.putString(dateOfIssue, dateOfIssueStr)
        editor.apply()
    }

}
