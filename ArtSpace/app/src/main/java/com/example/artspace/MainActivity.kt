package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Column(
            modifier = modifier
                .background(Color.White)
                .shadow(10.dp, shape = RoundedCornerShape(8.dp), clip = false)
                .padding(top = 40.dp, bottom = 30.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.mona_lisa),
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
                text = stringResource(R.string.mona_lisa),
                modifier = Modifier
                    .background(Color(android.graphics.Color.parseColor("#ECEBF4")))
                    .padding(125.dp,30.dp),
                color = Color.Black
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxHeight(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
        ){
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(end = 5.dp),

            ) {
            Text(text = "Previous")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 5.dp)
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        Greeting()
    }
}