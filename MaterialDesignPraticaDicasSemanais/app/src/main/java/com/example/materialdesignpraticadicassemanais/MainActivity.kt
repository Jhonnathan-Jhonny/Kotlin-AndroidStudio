@file:Suppress("UNUSED_EXPRESSION")

package com.example.materialdesignpraticadicassemanais

import android.os.Bundle
import android.util.Log
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
import com.example.materialdesignpraticadicassemanais.data.Dados
import com.example.materialdesignpraticadicassemanais.data.dataSourceList
import com.example.materialdesignpraticadicassemanais.ui.theme.MaterialDesignPraticaDicasSemanaisTheme


private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate Called")
        enableEdgeToEdge()
        setContent {
            MaterialDesignPraticaDicasSemanaisTheme {
                DayApp()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
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
fun DayView(dataSource: Dados, modifier: Modifier = Modifier){
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