package com.example.fly_practice_roomdatastore.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fly_practice_roomdatastore.FlyTopBar
import com.example.fly_practice_roomdatastore.R
import com.example.fly_practice_roomdatastore.data.Item
import com.example.fly_practice_roomdatastore.ui.navigation.NavigationDestination

object HomeDestination: NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)

//    val homeUiState by viewModel.homeUiState.collectAsState()
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier,
        topBar = {
            FlyTopBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                navigateUp = { }
            )
        }
    ) { innerPadding ->
        HomeBody(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            searchResults = viewModel.searchResults,
            onSearch = viewModel::searchAirports
        )
    }
}

@Composable
fun HomeBody(
    modifier: Modifier,
    searchResults: List<Item>,
    onSearch: (String) -> Unit)
{
    var textSearch by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = textSearch,
            onValueChange = { textSearch = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                IconButton(
                    onClick = {onSearch(textSearch)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Pesquisar"
                    )
                }
            },
            trailingIcon = {
                if (textSearch.isNotEmpty()) {
                    IconButton(onClick = { textSearch = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Limpar"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(textSearch)
                }
            ),
            singleLine = true
        )
        FlyList(
            itemList = searchResults,
            onFavoriteItemClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
fun FlyList(
    itemList: List<Item>,
    onFavoriteItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = itemList, key = { it.id }) { item ->
            FlyItem(
                item = item,
                onFavoriteItemClick = onFavoriteItemClick
            )
        }
    }
}

@Composable
fun FlyItem(
    item: Item,
    onFavoriteItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9F),
        ){
            Text(
                text = item.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
            Text(
                text = item.iataCode,
                modifier = Modifier
                    .padding(16.dp)
            )
            Text(
                text = item.passengers.toString(),
                modifier = Modifier
                    .padding(16.dp)
            )
            IconButton(
                onClick = { onFavoriteItemClick(item) },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    HomeBody(
        modifier = Modifier.fillMaxSize(),
        searchResults = listOf(
            Item(
                id = 1,
                name = "Francisco Sá Carneiro Airport",
                iataCode = "OPO",
                passengers = 5053134
            ),
            Item(
                id = 2,
                name = "Stockholm Arlanda Airport",
                iataCode = "ARN",
                passengers = 7494765
            )
        ),
        onSearch = {}
    )
}