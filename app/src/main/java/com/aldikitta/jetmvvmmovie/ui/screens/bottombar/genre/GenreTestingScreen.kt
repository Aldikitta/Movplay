package com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.data.model.Genres
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.utils.network.DataState

@Composable
fun GenreTestingScreen(
    navController: NavController,
    closeDrawer: (genreName: String) -> Unit
) {
    val genreTestingScreenViewModel = hiltViewModel<GenreTestingScreenViewModel>()
    val genres = genreTestingScreenViewModel.genres.value
    val itemsDrawer = listOf<Genre>()
    val genreName = remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        genreTestingScreenViewModel.genreListTestingScreen()
    }
    if (genres is DataState.Success<Genres>) {
        CallLazyCall(genres.data.genres, navController) {
            genreName.value = it
        }
    }
}

@Composable
fun CallLazyCall(
    genres: List<Genre>,
    navController: NavController,
    closeDrawer: (genreName: String) -> Unit

) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(start = 5.dp, top = 5.dp, end = 5.dp),
        content = {
            items(items = genres) { item ->
                LazyCall(item = item, onItemClick = {
                    navController.navigate(NavigationScreen.NAVIGATION_GENRE.plus("/${it.id}")) {
                        launchSingleTop = true
                        closeDrawer(it.name)
                    }
//                closeDrawer(it.name)
                })
            }
        }
    )
}

@Composable
fun LazyCall(
    item: Genre, onItemClick: (Genre) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.inversePrimary)
            .clickable { onItemClick(item) }
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        val primaryContainer = MaterialTheme.colorScheme.inversePrimary
        val surfaceVariant = MaterialTheme.colorScheme.primaryContainer

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = primaryContainer
            )
            drawPath(
                path = lightColoredPath,
                color = surfaceVariant
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        }
    }
}



