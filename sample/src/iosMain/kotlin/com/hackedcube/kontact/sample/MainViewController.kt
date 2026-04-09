package com.hackedcube.kontact.sample

import androidx.compose.ui.window.ComposeUIViewController
import com.hackedcube.kontact.IosKontactRepository

fun MainViewController() = ComposeUIViewController {
    App(IosKontactRepository())
}
