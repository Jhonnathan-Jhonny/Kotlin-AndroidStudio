@file:Suppress("UNUSED_EXPRESSION")

package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var atual by remember { mutableIntStateOf(1) }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ){

        when (atual) {
            1 -> {
                ChangeTheme(image = painterResource(id = R.drawable.mona_lisa), textImage = R.string.mona_lisa.toString())
            }
            2 -> {
                ChangeTheme(image = painterResource(id = R.drawable.ogrito), textImage = stringResource(
                    R.string.o_grito
                )
                )
            }
            else -> {
                ChangeTheme(image = painterResource(id = R.drawable.the_starry_night_van_gogh), textImage = stringResource(
                    R.string.van_gogh
                )
                )
            }
        }

    }
    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
        ){

        ButtomUser(onClick = {if(atual != 1) { atual-- } },atual, nomeButtom = "Previous")
        ButtomUser(onClick = {if(atual < 3) atual++ },atual, nomeButtom = "Next")
    }
}

@Composable
fun ButtomUser(
    onClick: (Int) -> Unit,
    valorAtual: Int,
    nomeButtom: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = { onClick(valorAtual) },
        modifier = Modifier
            .padding(start = 15.dp, bottom = 10.dp)
            .size(130.dp, 35.dp)
            .background(Color.White)
    ) {
        Text(text = nomeButtom)
    }
}

@Composable
fun ChangeTheme(
    image: Painter,
    textImage: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .background(Color.White)
            .shadow(10.dp, shape = RoundedCornerShape(8.dp), clip = false)
            .padding(top = 40.dp, bottom = 30.dp)
    ){
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .size(350.dp)
                .fillMaxHeight()
        )
    }
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = textImage,
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor("#ECEBF4")))
                .padding(125.dp, 30.dp),
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}