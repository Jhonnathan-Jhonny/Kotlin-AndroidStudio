package com.example.praticalistarolavel

import android.R
import android.R.attr.bottom
import android.R.attr.end
import android.R.attr.start
import android.R.attr.top
import android.R.style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praticalistarolavel.data.DataSource
import com.example.praticalistarolavel.model.Topic
import com.example.praticalistarolavel.ui.theme.PraticaListaRolavelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticaListaRolavelTheme {
            }
        }
    }
}

@Composable
fun TopicsApp(){

}

@Composable
fun TopicsCrad(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Box {
                Image(
                    painter =  painterResource(id = topic.imagem) ,
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Column {
            Text(
                text = stringResource(id = topic.name),
                modifier = Modifier.padding(16.dp,16.dp,16.dp,8.dp)
            )
            Row {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = stringResource(id = topic.courdesNumber),
                    modifier = modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PraticaListaRolavelTheme {
        TopicsCrad(DataSource.infoTopic.first())
    }
}