package com.example.androidproject.data.service

import android.telecom.Call
import com.example.androidproject.data.model.ItemsResponse
import okhttp3.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/nkuYRM")
    suspend fun getData(): retrofit2.Response<ItemsResponse>
}