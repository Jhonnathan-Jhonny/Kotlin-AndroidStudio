package com.example.prtica_viewmodel_listarolvel.helpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prtica_viewmodel_listarolvel.R
import com.example.prtica_viewmodel_listarolvel.data.DataSource
import com.example.prtica_viewmodel_listarolvel.model.UserModel
import com.example.prtica_viewmodel_listarolvel.ui.theme.AppTheme
import com.example.prtica_viewmodel_listarolvel.ui.theme.Shape

@Composable
fun AppScreen (appViewModel: AppViewModel = AppViewModel()){
    val appUiState by appViewModel.uiState.collectAsState()
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding()
    ){
        AppList(
            appList = DataSource.loadUsers(),
            appUiState = appUiState
        )
    }
}

@Composable
fun AppList(appList: List<UserModel>, modifier: Modifier = Modifier, appUiState: AppUiState) {
    LazyColumn (
        modifier = modifier
    ){
        items(appList){ appList ->
            AppCard(
                user = appList,
                appUiState = appUiState
                )
        }
    }

}

@Composable
fun AppCard(user: UserModel, appUiState: AppUiState) {
    Card(
        modifier = Modifier.run {
            fillMaxSize()
                .padding(bottom = 4.dp)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = user.avatar), contentDescription = "Avatar",
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
            )
            Text(
                text = stringResource(id = user.first_name),
                        modifier = Modifier
                            .align(alignment = CenterVertically)
                            .weight(1f),
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(alignment = CenterVertically)

            ) {
                Icon(
                    painter = if (!appUiState.buttonExpansive) painterResource(id = R.drawable.button_data_down) else painterResource(id = R.drawable.button_data_up),
                    contentDescription = "Button Down or up",
                    tint = Color.Black,
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        AppList(appList = DataSource.loadUsers(), appUiState = AppUiState())
    }
}