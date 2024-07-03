package com.example.materialdesign

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.materialdesign.data.DataDog
import com.example.materialdesign.data.listDog
import com.example.materialdesign.ui.theme.MaterialDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialDesignTheme {
                WoofApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) { paddingValues ->
        // O conteúdo principal é ajustado para respeitar o padding fornecido pelo Scaffold
        WoofList(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun WoofView(dataDog: DataDog, modifier: Modifier = Modifier){
    Card(modifier = Modifier.padding(8.dp)) {
        Row (
            modifier = Modifier
                .padding(8.dp),
        ){
            Image(
                painter = painterResource(id = dataDog.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
            )
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(id = dataDog.name),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
                Text(
                    text = "${dataDog.age} years old",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
fun WoofList(modifier: Modifier = Modifier){
    LazyColumn (
        modifier = modifier
            .padding(8.dp)
    ){
        items(listDog){ dog ->
            WoofView(dataDog = dog)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp),
                    painter = painterResource(R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = "Woof",
                    style = MaterialTheme.typography.displayLarge,
                )
            }
        },
    )
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    MaterialDesignTheme(darkTheme = true) {
        WoofApp()
    }
}
