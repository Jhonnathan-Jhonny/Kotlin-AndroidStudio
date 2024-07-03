package com.example.materialdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun WoofApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        WoofList()
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
fun WoofList(){
    LazyColumn (
        modifier = Modifier
            .padding(8.dp)
    ){
        items(listDog){ dog ->
            WoofView(dataDog = dog)
        }
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    MaterialDesignTheme(darkTheme = true) {
        WoofApp()
    }
}