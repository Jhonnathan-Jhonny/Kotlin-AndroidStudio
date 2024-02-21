package com.example.artigocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                        titulo = "Jetpack Compose tutorial",
                        introducao = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                        texto = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
                }
            }
        }
    }
}

@Composable
fun GreetingText(titulo: String, introducao: String, texto: String, modifier: Modifier = Modifier) {
    Text(
        text = titulo,
        modifier = modifier
    )
    Text(
        text = introducao
    )
    Text(
        text = texto
    )

}
@Composable
fun GreetingImage(titulo: String, introducao: String, texto: String, modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = "Imagem de início."
    )

    GreetingText(titulo = titulo, introducao = introducao, texto = texto)
}

@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    ArtigoComposeTheme {
        GreetingImage(titulo = "Jetpack Compose tutorial",introducao = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.", texto = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app's UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI's construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.")
    }
}