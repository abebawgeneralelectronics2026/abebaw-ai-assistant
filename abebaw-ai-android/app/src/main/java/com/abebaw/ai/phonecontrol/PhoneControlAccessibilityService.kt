package com.abebaw.ai.phonecontrol

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class PhoneControlAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // No phone actions are performed in this safe prototype.
    }

    override fun onInterrupt() {
        // No ongoing actions to interrupt.
    }
}
