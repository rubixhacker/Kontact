package com.hackedcube.kontact.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.hackedcube.kontact.DesktopKontactRepository

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kontact",
    ) {
        App(DesktopKontactRepository())
    }
}
