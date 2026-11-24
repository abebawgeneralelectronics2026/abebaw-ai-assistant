package com.abebaw.ai.phonecontrol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private lateinit var statusText: TextView

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
        }

        statusText = TextView(this).apply {
            textSize = 16f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
            setPadding(0, 35, 0, 35)
        }

        val enableButton = Button(this).apply {
            text = "OPEN ACCESSIBILITY SETTINGS"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }

        val stopButton = Button(this).apply {
            text = "STOP / PAUSE CONTROL"
            setOnClickListener {
                statusText.text =
                    "Control paused. You are in charge of your phone."
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(statusText)
        layout.addView(enableButton)
        layout.addView(stopButton)

        setContentView(layout)
    }

    override fun onResume() {
        super.onResume()
        statusText.text =
            "Phone control is not active yet.\nYour permission is required."
    }
}
