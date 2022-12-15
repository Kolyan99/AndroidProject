package com.example.androidproject.presentation.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidproject.R
import com.example.androidproject.presentation.view.view.dataBinding.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = supportFragmentManager.beginTransaction()//полезно для дз(вложенность)
        fragmentTransaction.add(R.id.activity_container, LoginFragment())
        fragmentTransaction.commit()

    }
}