package com.qdaidu.autorent.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.Navigator


class PreferenceManagerNameData(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    companion object {
        private const val name = "name"
        private const val surname = "surname"
        private const val secondName = "secondName"
        private const val sex = "sex"
        private const val birthDate = "birthDate"

    }

    fun getName(): String? {
        return preferences.getString(name, null)
    }

    fun setName(loginStr: String){
        val editor = preferences.edit()
        editor.putString(name, loginStr)
        editor.apply()
    }

    fun getSurname(): String? {
        return preferences.getString(surname, null)
    }

    fun setSurname(loginStr: String){
        val editor = preferences.edit()
        editor.putString(surname, loginStr)
        editor.apply()
    }
    fun getSecondName(): String? {
        return preferences.getString(secondName, null)
    }

    fun setSecondName(loginStr: String){
        val editor = preferences.edit()
        editor.putString(secondName, loginStr)
        editor.apply()
    }
    fun getSex(): Int {
        return preferences.getInt(sex, 0)
    }

    fun setSex(sexInt: Int){
        val editor = preferences.edit()
        editor.putInt(sex, sexInt)
        editor.apply()
    }
    fun getBirthDate(): String? {
        return preferences.getString(birthDate, null)
    }

    fun setBirthDate(birthDateStr: String){
        val editor = preferences.edit()
        editor.putString(birthDate, birthDateStr)
        editor.apply()
    }

}
