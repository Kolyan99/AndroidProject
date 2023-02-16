package com.example.androidproject.data.service

import android.telecom.Call
import com.example.androidproject.data.model.ItemsResponse
import io.reactivex.Single
import okhttp3.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/nkuYRM")
     fun getData(): Single<ItemsResponse>
}