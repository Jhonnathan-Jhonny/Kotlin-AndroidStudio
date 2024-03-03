@file:Suppress("UNUSED_EXPRESSION")

package com.example.lemonade

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {

    var resultApp by remember { mutableIntStateOf(1) }
    var resultRandom by remember { mutableIntStateOf(0) }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Row(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        when(resultApp){
            1-> {
                LemonadeTextAndImage(
                    text = stringResource(R.string.text1),
                    image = painterResource(id = R.drawable.lemon_tree),
                    onImageClick = {
                        resultApp = 2
                        resultRandom = (2..4).random()
                    }
                )
            }
            2->{
                LemonadeTextAndImage(
                    text = stringResource(R.string.text2),
                    image = painterResource(id = R.drawable.lemon_squeeze),
                    {
                        resultRandom--
                        if (resultRandom == 0){
                            resultApp = 3
                        }
                    }
                )
            }
            3-> {
                LemonadeTextAndImage(
                    text = stringResource(R.string.text3),
                    image = painterResource(id = R.drawable.lemon_drink),
                    {resultApp = 4}
                )
            }
            else-> {
                LemonadeTextAndImage(
                    text = stringResource(R.string.text4),
                    image = painterResource(id = R.drawable.lemon_restart),
                    {resultApp = 1}
                )
            }
        }
    }

}

@Composable
fun LemonadeTextAndImage(
    text: String,
    image: Painter,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()

    ){
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                )
        }
        Text(
            text = text,
            color = Color.Black,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}