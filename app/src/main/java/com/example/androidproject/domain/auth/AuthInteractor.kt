package com.example.androidproject.domain.auth

import com.example.androidproject.domain.model.UserModel
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository) {

     suspend fun loginUser( userName: String, userPassword: String){
        authRepository.loginUser(userName, userPassword)
    }

   suspend fun getUserCreds(): UserModel{
        return authRepository.showUserCreds()
    }

   suspend fun checkUserExsist(): Boolean{
        return authRepository.doesUserExists()
    }

    suspend fun logoutUser(){
        authRepository.userLogout()
    }
}