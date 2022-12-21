package com.example.androidproject.domain.auth

import com.example.androidproject.domain.model.UserModel

interface AuthRepository {

    fun loginUser(userName: String, userPassword: String)

    fun showUserCreds():UserModel

    fun doesUserExists(): Boolean

    fun userLogout()
}