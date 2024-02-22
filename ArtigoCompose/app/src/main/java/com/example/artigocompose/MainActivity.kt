package com.example.artigocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artigocompose.ui.theme.ArtigoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtigoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(
                        titulo = getString(R.string.Titulo),
                        introducao = getString(R.string.paragrafo1),
                        texto = getString(R.string.paragrafo2))
                }
            }
        }
    }
}

@Composable
fun GreetingText(titulo: String, introducao: String, texto: String, modifier: Modifier = Modifier) {
    Column(
        Modifier.background(Color.White)
    ) {
        Text(
            text = titulo,
            modifier = modifier
                .padding(10.dp),
            fontSize = 25.sp,
            color = Color.Black
        )
        Text(
            text = introducao,
            modifier = modifier.padding(10.dp),
            color = Color.Black,
            textAlign = TextAlign.Justify
        )
        Text(
            text = texto,
            modifier = modifier.padding(10.dp),
            color = Color.Black,
            textAlign = TextAlign.Justify

        )
    }

}
@Composable
fun GreetingImage(titulo: String, introducao: String, texto: String, modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column(
        modifier = modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        ){
        Image(
            painter = image,
            contentDescription = null,
        )

        GreetingText(titulo = titulo, introducao = introducao, texto = texto)
    }

}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    ArtigoComposeTheme {
        GreetingImage(titulo = "Jetpack Compose tutorial",introducao = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.", texto = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
    }
}