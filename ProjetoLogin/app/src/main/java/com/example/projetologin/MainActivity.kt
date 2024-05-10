package com.example.projetologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projetologin.ui.theme.ProjetoLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetoLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LayoutInitialLogin()
                }
            }
        }
    }
}

@Composable
fun LayoutInitialLogin() {
    Column (
    ){

    }
}

@Composable
fun InformationBar(
    value: String,
    onValueChange: (String) -> Unit
){
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjetoLoginTheme {
        LayoutInitialLogin()
    }
}