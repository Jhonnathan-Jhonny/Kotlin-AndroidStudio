package com.example.pontuao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pontuao.ui.theme.PontuaçãoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PontuaçãoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LayoutInicial()
                }
            }
        }
    }
}

@Composable
fun LayoutInicial(modifier: Modifier = Modifier) {
    Column (Modifier.fillMaxSize()){
        Column (
            modifier = Modifier.background(Color.Blue),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(text = "0")
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "+")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "-")
                }
            }
        }
        Column (modifier = Modifier.background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = "0")
            Row {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "+")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "-")
                }
            }
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PontuaçãoTheme {
        LayoutInicial()
    }
}