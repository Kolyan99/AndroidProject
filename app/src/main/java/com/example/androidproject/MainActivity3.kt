package com.example.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val btn = findViewById<Button>(R.id.btnGoToActivity1)

        btn.setOnClickListener {
            startActivity(
                Intent("com.example.androidproject.OPEN_ACTIVITY1")
            )
        }
    }
}


