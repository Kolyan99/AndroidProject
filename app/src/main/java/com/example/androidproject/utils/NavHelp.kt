package com.example.androidproject.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

object NavHelp {


    fun Fragment.navigate(destination: Int){
        findNavController().navigate(destination)
    }

    fun Fragment.replaceGraph(graph: Int){
        findNavController().setGraph(graph)
    }

    fun Fragment.navigateWithDeleteBackStack(destination: Int, fragmentToRemove: Int){
        val navOption = NavOptions.Builder()
        navOption.setPopUpTo(fragmentToRemove, true)
        findNavController().navigate(
            destination,
            null,
            navOption.build()
        )
    }

    fun Fragment.navigateWithBundle(destination: Int, bundel: Bundle){
        findNavController().navigate(destination,bundel)
    }

}