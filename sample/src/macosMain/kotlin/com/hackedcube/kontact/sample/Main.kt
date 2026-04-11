package com.hackedcube.kontact.sample

import androidx.compose.ui.window.Window
import com.hackedcube.kontact.AppleKontactRepository
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    Window(title = "Kontact") {
        App(AppleKontactRepository())
    }
    NSApp?.run()
}
