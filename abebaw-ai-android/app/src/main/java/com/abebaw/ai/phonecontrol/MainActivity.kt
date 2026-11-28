
package com.abebaw.ai.phonecontrol

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var taskInput: EditText
    private lateinit var preview: TextView
    private lateinit var approveButton: Button
    private var reviewedTask = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 32, 28, 32)
            setBackgroundColor(0xFF101522.toInt())
        }

        val scroll = ScrollView(this)
        scroll.addView(root)

        fun addText(
            value: String,
            size: Float,
            color: Int
        ): TextView {
            return TextView(this).apply {
                text = value
                textSize = size
                setTextColor(color)
                gravity = Gravity.CENTER
                setPadding(8, 12, 8, 12)
            }
        }

        val gold = 0xFFD4AF37.toInt()
        val white = 0xFFFFFFFF.toInt()

        root.addView(addText("ABEB AW AI", 30f, gold))
        root.addView(addText("PHONE CONTROL", 19f, white))
        root.addView(
            addText(
                "Your assistant. Your choices. Your control.",
                14f,
                white
            )
        )

        root.addView(
            addText("What would you like me to do?", 20f, gold)
        )

        taskInput = EditText(this).apply {
            hint = "Describe your task here..."
            setHintTextColor(0xFFAAAAAA.toInt())
            setTextColor(white)
            textSize = 16f
            minLines = 3
            gravity = Gravity.TOP
            setPadding(16, 16, 16, 16)
            setBackgroundColor(0xFF263044.toInt())
        }

        root.addView(
            taskInput,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        val reviewButton = Button(this).apply {
            text = "REVIEW TASK"
            setOnClickListener {
                val task = taskInput.text.toString().trim()

                if (task.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Please describe a task first.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    reviewedTask = task
                    preview.text =
                        "TASK REVIEW\n\n" +
                        "Your request:\n$task\n\n" +
                        "This is a demonstration only. " +
                        "No phone action will be performed."
                    approveButton.isEnabled = true
                }
            }
        }
        root.addView(reviewButton)

        preview = addText(
            "Your task plan will appear here after review.",
            16f,
            white
        )
        root.addView(preview)

        approveButton = Button(this).apply {
            text = "APPROVE DEMO"
            isEnabled = false
            setOnClickListener {
                if (reviewedTask.isNotEmpty()) {
                    confirmApproval()
                }
            }
        }
        root.addView(approveButton)

        val cancelButton = Button(this).apply {
            text = "CANCEL"
            setOnClickListener {
                reviewedTask = ""
                taskInput.setText("")
                preview.text = "Task cancelled. No phone action was performed."
                approveButton.isEnabled = false
            }
        }
        root.addView(cancelButton)

        root.addView(
            addText(
                "SAFE PROTOTYPE\n" +
                "No phone actions are enabled in this version.",
                14f,
                gold
            )
        )

        setContentView(scroll)
    }

    private fun isSensitiveTask(task: String): Boolean {
        val words = listOf(
            "delete", "remove", "erase", "format",
            "reset", "password", "security", "settings",
            "uninstall", "payment", "transfer", "money"
        )

        val text = task.lowercase()
        return words.any { text.contains(it) }
    }

    private fun confirmApproval() {
        AlertDialog.Builder(this)
            .setTitle("Confirm your approval")
            .setMessage(
                "Do you approve this demonstration request?\n\n" +
                reviewedTask +
                "\n\nNo phone action will be performed."
            )
            .setNegativeButton("CANCEL", null)
            .setPositiveButton("APPROVE") { _, _ ->
                if (isSensitiveTask(reviewedTask)) {
                    confirmSensitiveTask()
                } else {
                    recordDemoApproval()
                }
            }
            .show()
    }

    private fun confirmSensitiveTask() {
        AlertDialog.Builder(this)
            .setTitle("Extra confirmation required")
            .setMessage(
                "This request may involve a sensitive action.\n\n" +
                "Are you absolutely sure you want to approve " +
                "this demonstration?\n\n" +
                "No real action will be performed."
            )
            .setNegativeButton("CANCEL", null)
            .setPositiveButton("CONFIRM") { _, _ ->
                recordDemoApproval()
            }
            .show()
    }

    private fun recordDemoApproval() {
        preview.text =
            "Demo approval recorded.\n\n" +
            "No phone action was performed."

        approveButton.isEnabled = false

        Toast.makeText(
            this,
            "Demo approval recorded.",
            Toast.LENGTH_LONG
        ).show()
    }
}
