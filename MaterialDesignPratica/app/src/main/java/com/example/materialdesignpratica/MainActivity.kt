package com.example.materialdesignpratica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                HeroesScreen(heroes.first())
            }
        }
    }
}

@Composable
fun HeroesScreen(hero: Hero, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = stringResource(id = hero.nameRes))
            Text(text = stringResource(id = hero.descriptionRes))
        }
        Image(
            painter = painterResource(id = hero.imageRes),
            contentDescription = stringResource(id = hero.descriptionRes),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialDesignPraticaTheme {
        HeroesScreen(
                Hero(
                    nameRes = R.string.hero1,
                    descriptionRes = R.string.description1,
                    imageRes = R.drawable.gril
                )
        )
    }
}
