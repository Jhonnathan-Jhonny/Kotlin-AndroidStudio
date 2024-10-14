package com.example.prtica_viewmodel_listarolvel.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.prtica_viewmodel_listarolvel.Greeting
import com.example.prtica_viewmodel_listarolvel.ui.theme.PráticaViewModellistaRolávelTheme

@Composable
fun AppScreen (appViewModel: AppViewModel = AppViewModel()){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PráticaViewModellistaRolávelTheme {
        Greeting("Android")
    }
}