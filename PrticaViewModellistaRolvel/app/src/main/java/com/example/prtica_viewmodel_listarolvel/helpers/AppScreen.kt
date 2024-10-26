package com.example.prtica_viewmodel_listarolvel.helpers

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prtica_viewmodel_listarolvel.R
import com.example.prtica_viewmodel_listarolvel.model.UserModel
import com.example.prtica_viewmodel_listarolvel.ui.theme.AppTheme
import com.example.prtica_viewmodel_listarolvel.ui.theme.Shape

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen(appViewModel: AppViewModel = AppViewModel()) {
    val userList by appViewModel.userList.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(),
        topBar = { AppTopBar() }
    ) { paddingValues ->
        AppList(
            appList = userList,
            onIconClick = { userId -> appViewModel.toggleButtonExpansive(userId) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}


@Composable
fun AppList(
    appList: List<UserModel>,
    onIconClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(appList) { user ->
            AppCard(
                user = user,
                onIconClick = { onIconClick(user.number) }
            )
        }
    }
}

@Composable
fun AppCard(user: UserModel, onIconClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(4.dp),
        colors = CardDefaults.cardColors(colorScheme.secondary),
        shape = Shape.medium
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = user.avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(40.dp)
                )
                Text(
                    text = stringResource(id = user.first_name),
                    modifier = Modifier
                        .align(CenterVertically)
                        .weight(1f)
                )
                IconButton(
                    onClick = onIconClick,
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Icon(
                        painter = if (user.buttonExpansive) {
                            painterResource(id = R.drawable.button_data_up)
                        } else {
                            painterResource(id = R.drawable.button_data_down)
                        },
                        contentDescription = "Expand/Collapse Button",
                        tint = Color.White,
                    )
                }
            }
            // Exibir informações adicionais se o botão expansivo estiver ativo
            if (user.buttonExpansive) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 72.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "${stringResource(id = user.first_name)} ${stringResource(id = user.last_name)}",
                        fontSize = 20.sp,
                        color = Color.White                    )
                    Text(
                        text = stringResource(id = user.number),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Row (
                verticalAlignment = CenterVertically
            ){
                Text(
                    text = "Contatos"
                )
            }
        },
    )
}


@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AppTheme {
        AppScreen(appViewModel = AppViewModel())
    }
}
