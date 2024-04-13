package com.example.pontuao

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pontuao.ui.theme.PontuaçãoTheme
import androidx.compose.material3.Switch


class MainActivity : ComponentActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_main->{
                println("main menu clicado")
                item.isChecked = !item.isChecked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PontuaçãoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LayoutInicial()
                }
            }
        }
    }
}


@Composable
fun LayoutInicial(modifier: Modifier = Modifier) {
    var result1 by remember { mutableIntStateOf(0) }
    var temaEscuro by remember { mutableStateOf(false) }
    val placar1: Int = result1
    var time1 by remember { mutableStateOf("") }
    var usando1 by remember { mutableStateOf(false) }


    Column (){
        Row (
            modifier
                .background(Color.White)
                .padding(20.dp)
                .fillMaxWidth()
        ){

        }
        Column (
            modifier = Modifier
                .background(
                    if (temaEscuro) {
                        Color.Gray
                    } else {
                        Color.Blue
                    }
                )
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(
                text = placar1.toString(),
                fontSize = 100.sp,
                color = Color.White
            )
            NomeTime(value = time1, onValueChange = {time1 = it}, {usando1},onUsandoChange = { usando1 = it })
            Row(
            ) {
                Button(
                    onClick = {if (placar1 != 0){result1--}},
                    modifier = Modifier
                        .padding(end = 50.dp, bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)

                    ) {
                    Text(
                            text = "- ",
                        fontSize = 40.sp,
                        )
                }
                Button(
                    onClick = {result1++},
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "+ ",
                        fontSize = 30.sp
                    )
                }
            }
        }
        var result2 by remember { mutableIntStateOf(0) }
        val placar2: Int = result2
        var time2 by remember { mutableStateOf("") }
        var usando2 by remember { mutableStateOf(false) }


        Column (modifier = Modifier
            .background(
                if (temaEscuro) {
                    Color.Black
                } else {
                    Color.Red
                }
            )
            .weight(1f)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = result2.toString(),
                fontSize = 100.sp,
                color = Color.White
            )
            MudarTema(temaEscuro = temaEscuro, onTemaEscuro = {temaEscuro = it})
            NomeTime(value = time2, onValueChange = {time2 = it}, {usando2},onUsandoChange = { usando2 = it })

            Row{
                Button(
                    onClick = { if (placar2 != 0){result2--} },
                    modifier = Modifier
                        .padding(end = 50.dp, bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                    ) {
                    Text(
                        text = "-",
                        fontSize = 40.sp,
                    )
                }
                Button(
                    onClick = { result2++ },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 150.dp, height = 75.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(
                        text = "+",
                        fontSize = 30.sp
                    )
                }
            }
        }
        Column (){
            Button(
                onClick = {
                    result1 = 0
                    result2 = 0
                    usando1 = false
                    usando2 = false
                    time1 = ""
                    time2 = ""
                },
                modifier
                    .fillMaxWidth()
                    .background(
                        if (temaEscuro) {
                            Color.White
                        } else {
                            Color.Yellow
                        }
                    )
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    if (temaEscuro){
                    Color.White
                    }
                    else{Color.Yellow}),
            ) {
                Text(
                    text = "Zerar",
                    fontSize = 20.sp,
                    color = if (temaEscuro) {
                        Color(android.graphics.Color.parseColor("#000000"))
                    } else {
                        Color(android.graphics.Color.parseColor("#FFFFFF"))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NomeTime(
    value: String,
    onValueChange: (String) -> Unit,
    usando: () -> Boolean,
    onUsandoChange: (Boolean) -> Unit, // Função para alterar o estado "usando"
){
    Row {
        if (!usando()){ // Usando a função "usando" para verificar o estado
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(200.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ),
                label = { Text(
                    text = "Nome do time",
                    Modifier.alpha(0.5f)
                )},
            )
            Button(
                onClick = { onUsandoChange(true) } // Chamando a função para alterar o estado "usando"
            ) {
                Text(text = "Confirmar")
            }
        }
        if (usando()) { // Usando a função "usando" para verificar o estado
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = value,
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    textAlign = TextAlign.Center
                )
                Button(onClick = { onUsandoChange(false) }) {
                    Text(text = "Editar")
                }
            }

        }
    }

}

@Composable
fun MudarTema(
    temaEscuro:Boolean,
    onTemaEscuro: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Row (

    ){
        Switch(
            checked = temaEscuro,
            onCheckedChange = onTemaEscuro,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PontuaçãoTheme {
        LayoutInicial()
    }
}