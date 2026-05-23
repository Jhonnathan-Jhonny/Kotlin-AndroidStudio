package com.project.amphibians.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.amphibians.R
import com.project.amphibians.model.AmphibiansModel
import com.project.amphibians.network.AmphibiansUiState

val mockAmphibian = listOf(
    AmphibiansModel(
        name = "Tiger Salamander",
        type = "Salamander",
        description = "Large salamander species.",
        imgSrc = ""
    )
)

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> ResultScreen(mockAmphibian, modifier = modifier.fillMaxWidth())
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(amphibians: List<AmphibiansModel>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
    ) {
        items(
            items = amphibians, key = {amphibians -> amphibians.name}
        ) {amphibians ->
            AmphibiansCard(
                amphibians = amphibians,
                modifier = modifier
            )
        }
    }
}

@Composable
fun AmphibiansCard(amphibians: AmphibiansModel, modifier: Modifier) {
    Card (
        modifier
            .fillMaxWidth(),
        // Add padding to the Card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDEE5D8),
        )
    ){
        Text(
            text = amphibians.name + " (${amphibians.type})",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
        )
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(amphibians.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = "Amphibian image",
            error = painterResource(R.drawable.ic_broken_image),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.loading_img),
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = amphibians.description,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
        )

    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun HomeScreenSuccessPreview() {
    HomeScreen(
        amphibiansUiState = AmphibiansUiState.Success(mockAmphibian),
        retryAction = {}
    )
}