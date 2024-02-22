package com.example.gerenciadodetarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gerenciadodetarefas.ui.theme.GerenciadoDeTarefasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadoDeTarefasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage()
                }
            }
        }
    }
}

@Composable
fun GreetingText(modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.all_tasks_completed),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(0.dp, 24.dp, 0.dp, 8.dp),
            color = Color.Black
        )
        Text(
            text = stringResource(R.string.nice_work),
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 38.dp),
        )
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier){
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Image(
            painter = image,
            contentDescription = null,
        )
        GreetingText()
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GerenciadoDeTarefasTheme {
        GreetingImage()
    }
}