package com.example.materialdesignpratica

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.materialdesignpratica.data.Hero
import com.example.materialdesignpratica.data.HeroesRepository.heroes
import com.example.materialdesignpratica.ui.theme.MaterialDesignPraticaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialDesignPraticaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeroesApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HeroesApp() {
    Scaffold(
        topBar = { TopAppBar() },
    ) {
        HeroesList()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically,
            ){
                Text(
                    text = "Heroes",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    )
}

@Composable
fun HeroesScreen(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)

                )
                Text(text = stringResource(id = hero.descriptionRes))
            }
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.descriptionRes),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun HeroesList(){
    LazyColumn {
        items(heroes) { hero ->
            HeroesScreen(hero = hero)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialDesignPraticaTheme {
//        HeroesScreen(  Hero(
//            nameRes = R.string.hero3,
//            descriptionRes = R.string.description3,
//            imageRes = R.drawable.aranha
//        ))
        HeroesApp()
    }
}
