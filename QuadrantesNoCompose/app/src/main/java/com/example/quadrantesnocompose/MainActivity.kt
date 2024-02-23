package com.example.quadrantesnocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quadrantesnocompose.ui.theme.QuadrantesNoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadrantesNoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingText()
                }
            }
        }
    }
}

@Composable
fun GreetingText(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (Modifier.weight(1f)){
            ComposeQuadranteCrad(
                titulo = "Text composable",
                description = "Displays text and follows the recommended Material Design guidelines.",
                corDeFundo = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f),
            )
            ComposeQuadranteCrad(
                titulo = "Image composable",
                description = "Creates a composable that lays out and draws a given Painter class object.",
                corDeFundo = Color(0xFFD0BCFF),
                modifier = Modifier.weight(1f)
            )

        }
        Row(Modifier.weight(1f)) {
            ComposeQuadranteCrad(
                titulo = "Row composable",
                description = "A layout composable that places its children in a horizontal sequence.",
                corDeFundo = Color(0xFFB69DF8),
                modifier = Modifier.weight(1f)
            )
            ComposeQuadranteCrad(
                titulo = "Column composable",
                description = "A layout composable that places its children in a vertical sequence.",
                corDeFundo = Color(0xFFEADDFF),
                modifier = Modifier.weight(1f)
            )
        }
    }

}

@Composable
fun ComposeQuadranteCrad(
    titulo: String,
    description: String,
    corDeFundo: Color,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(corDeFundo)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titulo,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            color = Color.Black
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuadrantesNoComposeTheme {
        GreetingText()
    }
}