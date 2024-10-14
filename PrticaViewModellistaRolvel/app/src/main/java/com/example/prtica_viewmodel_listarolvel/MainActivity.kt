package com.example.prtica_viewmodel_listarolvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.prtica_viewmodel_listarolvel.ui.theme.AppTheme
import com.example.prtica_viewmodel_listarolvel.helpers.AppScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface (modifier = Modifier.fillMaxSize()){
                    AppScreen()
                }
            }
        }
    }
}