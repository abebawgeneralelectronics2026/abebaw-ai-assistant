package com.abebaw.ai.phonecontrol

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 40, 32, 40)
            setBackgroundColor(0xFF101522.toInt())
        }

        val title = TextView(this).apply {
            text = "ABEB AW AI"
            textSize = 30f
            setTextColor(0xFFD4AF37.toInt())
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "PHONE CONTROL"
            textSize = 18f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
            setPadding(0, 12, 0, 24)
        }

        val status = TextView(this).apply {
            text = "Safe preview build\n\nPhone-control actions are not enabled in this version.\nNo Accessibility Service is included."
            textSize = 16f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
            setLineSpacing(6f, 1f)
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(status)
        setContentView(layout)
    }
}
