package com.hackedcube.kontact.sample

import androidx.compose.ui.window.ComposeUIViewController
import com.hackedcube.kontact.AppleKontactRepository

fun MainViewController() = ComposeUIViewController {
    App(AppleKontactRepository())
}
