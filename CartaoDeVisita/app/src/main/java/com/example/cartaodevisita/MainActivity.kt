package com.example.cartaodevisita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cartaodevisita.ui.theme.CartaoDeVisitaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaoDeVisitaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val imgInst = painterResource(id = R.drawable.instagram)
    val imgTelefone = painterResource(id = R.drawable.telefone)
    val imgGit = painterResource(id = R.drawable.git)
    val imgEu = painterResource(id = R.drawable.profile_pic__2____copia)
    val imgFundo = painterResource(id = R.drawable.fundo)
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = imgFundo,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.3F),
            alpha = 0.3F

        )
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ){
            Image(
                painter = imgEu,
                contentDescription = null,
                modifier = modifier.size(150.dp,150.dp),
            )
            Text(
                text = stringResource(R.string.jhonnathan_rodrigues),
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
            Text(
                text = "Desenvolvedor android",
                modifier.padding(bottom = 35.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Contatos(
                imagem = imgTelefone,
                descricao = "+55 (83) 9 9991-2744",
                Modifier.padding(top = 40.dp)
            )
            Contatos(imagem = imgGit, descricao = "Jhonnathan-Johnny", Modifier.padding(end = 5.dp))
            Contatos(
                imagem = imgInst,
                descricao = "@Jhonnathan_Jhonny",
            )
        }

    }
}

@Composable
fun Contatos(imagem: Painter, descricao: String, modifier: Modifier = Modifier){
    Row(
        modifier
            .padding(10.dp)
    ){
        Image(
            painter = imagem,
            contentDescription = null,
            modifier = modifier
                .size(50.dp, 50.dp)
                .padding(end = 5.dp)
        )
        Text(
            text = descricao,
            modifier.padding(top = 15.dp),
            color = Color.Black,
            fontSize = 20.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CartaoDeVisitaTheme {
        Greeting("Android")
    }
}