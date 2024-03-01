package com.example.pontuao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
    var result1 by remember { mutableIntStateOf(0) }
    var placar1: Int = result1
    Column (){
        Column (
            modifier = Modifier
                .background(Color.Blue)
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(
                text = placar1.toString(),
                fontSize = 150.sp,
                color = Color.White
            )
            Text(
                text = "Time 1",
                Modifier.padding(bottom = 10.dp),
                fontSize = 30.sp,
                color = Color.White
            )
            Row(
            ) {
                Button(
                    onClick = {if (placar1 != 0){result1--}},
                    modifier = Modifier
                        .padding(end = 50.dp, bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)

                    ) {
                    Text(
                        text = "-",
                        fontSize = 40.sp,
                        )
                }
                Button(
                    onClick = {result1++},
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "+",
                        fontSize = 30.sp
                    )
                }
            }
        }
        var result2 by remember { mutableIntStateOf(0) }
        var placar2: Int = result2
        Column (modifier = Modifier
            .background(Color.Red)
            .weight(1f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = result2.toString(),
                fontSize = 150.sp,
                color = Color.White
            )
            Text(
                text = "Time 2",
                Modifier.padding(bottom = 10.dp),
                fontSize = 30.sp,
                color = Color.White
                )
            Row{
                Button(
                    onClick = { if (placar2 != 0){result2--} },
                    modifier = Modifier
                        .padding(end = 50.dp, bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                    Text(
                        text = "-",
                        fontSize = 40.sp,
                    )
                }
                Button(
                    onClick = { result2++ },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "+",
                        fontSize = 30.sp
                    )
                }
            }
        }
        Column (){
            Button(
                onClick = {
                    result1 = 0 
                    result2 = 0
                },
                modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Yellow),
            ) {
                Text(
                    text = "Zerar",
                    fontSize = 20.sp,
                )
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