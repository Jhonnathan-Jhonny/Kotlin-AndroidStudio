@file:Suppress("UNUSED_EXPRESSION")

package com.example.materialdesignpraticadicassemanais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.materialdesignpraticadicassemanais.data.DataSource
import com.example.materialdesignpraticadicassemanais.data.dataSourceList
import com.example.materialdesignpraticadicassemanais.ui.theme.MaterialDesignPraticaDicasSemanaisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialDesignPraticaDicasSemanaisTheme {
                DayApp()
            }
        }
    }
}

@Composable
fun DayApp(){
    Scaffold {
        it
        DayList()
    }
}

@Composable
fun DayView(dataSource: DataSource,modifier: Modifier = Modifier){
    var expanded by remember { mutableStateOf(false) }
    Card{
        Column {
            Text(
                text = stringResource(id = dataSource.day),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Button(
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = dataSource.image),
                    contentDescription = null,
                    Modifier
                        .size(250.dp, 160.dp)
                        .padding(10.dp, 0.dp)
                )
            }
        }
        if (expanded){
            PhraseDay(phrase = dataSource.text)
        }
    }
}

@Composable
fun DayList(){
    LazyColumn {
        items(dataSourceList){ dayList->
            DayView(dayList)
        }
    }
}

@Composable
fun PhraseDay(@StringRes phrase: Int){
    Column {
        Text(
            text = stringResource(id = phrase),
            fontSize = 20.sp,
            modifier = Modifier
                .size(250.dp,50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialDesignPraticaDicasSemanaisTheme {
        DayApp()
    }
}