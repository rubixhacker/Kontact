package com.hackedcube.kontact.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hackedcube.kontact.AndroidKontactRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = AndroidKontactRepository(this)
        setContent {
            App(repository)
        }
    }
}
