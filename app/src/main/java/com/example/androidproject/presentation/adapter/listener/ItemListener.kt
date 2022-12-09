package com.example.androidproject.presentation.adapter.listener

import android.widget.ImageView
import java.util.Date

interface ItemListener {

    fun onClick()

    fun onElementSelected( name: String, date: String, imageView: Int)
}