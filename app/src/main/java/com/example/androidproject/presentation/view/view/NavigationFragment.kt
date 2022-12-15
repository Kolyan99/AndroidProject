package com.example.androidproject.presentation.view.view

import android.provider.Settings.System.getString
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidproject.R

object NavigationFragment {

    fun Repit(parentFragmentManager: FragmentManager, fragment: Fragment){
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, fragment)
            .addToBackStack((R.string.OnBoarding.toString()))
            .commit()
    }
}