package com.example.androidproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnGoToActivity3 = findViewById<Button>(R.id.btnGoToActivity3)

        btnGoToActivity3.setOnClickListener {
            startActivity(
                Intent(this, MainActivity3::class.java))
        }

    }

    //Выход из приложения через кнопку назад
//    override fun onBackPressed() {
//        super.onBackPressed()
//        finishAffinity()
//    }
}