package com.example.androidproject.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CustomButton @JvmOverloads constructor(
    context: Context, attribute: AttributeSet? = null, style: Int = 0
): AppCompatButton(context, attribute, style) {

    val painter = Paint().apply {
        color = Color.GREEN
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(Rect(0,0, width, height), painter)
    }
}