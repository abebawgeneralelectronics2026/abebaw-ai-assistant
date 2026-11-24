package com.abebaw.ai.phonecontrol

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class PhoneControlAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Screen events will be handled by the approved command engine later.
    }

    override fun onInterrupt() {
        // Stop any ongoing accessibility interaction.
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        // The service is connected but does not execute commands yet.
    }
}
