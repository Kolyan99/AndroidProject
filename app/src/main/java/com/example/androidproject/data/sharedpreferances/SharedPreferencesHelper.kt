package com.example.androidproject.data.sharedpreferances

import android.content.SharedPreferences
import com.example.androidproject.domain.model.UserModel
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    private val sharedPreference: SharedPreferences
) {

    fun saveUserName(name: String?){
        sharedPreference.edit().putString(USER_NAME, name ).apply()
    }

    fun saveUserPassword(password: String?){
        sharedPreference.edit().putString(USER_Password, password ).apply()
    }

    fun getUserCreds(): UserModel{
        val name = sharedPreference.getString(USER_NAME, "")?: "No user"
        val password = sharedPreference.getString(USER_Password, "")?: "No password"
        return UserModel(name, password)
    }

    fun checkUserExists(): Boolean{
        val name = sharedPreference.getString(USER_NAME, "")
        val password = sharedPreference.getString(USER_Password, "")
        return (!name.isNullOrEmpty() && !password.isNullOrEmpty())
    }

    fun removeUser(){
        saveUserName(null)
        saveUserPassword(null)
    }

    companion object{
        private const val USER_NAME = "User_Name"
        private const val USER_Password = "User_Password"
    }
}