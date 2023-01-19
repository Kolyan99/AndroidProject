package com.example.androidproject.presentation.view.view.auth.home.adapter.listener

import android.widget.ImageView
import java.util.Date

interface ItemListener {

    fun onClick()

    fun onElementSelected( description: String, image: String)
}