package com.example.androidproject.presentation.view.view.adapter.listener

import android.widget.ImageView
import java.util.Date

interface ItemListener {

    fun onClick()

    fun onElementSelected( name: String, date: String, imageView: Int)
}