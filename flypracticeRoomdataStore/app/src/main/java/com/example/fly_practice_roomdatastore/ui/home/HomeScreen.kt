package com.example.fly_practice_roomdatastore.ui.home

import androidx.compose.runtime.Composable
import com.example.fly_practice_roomdatastore.R
import com.example.fly_practice_roomdatastore.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(){

}