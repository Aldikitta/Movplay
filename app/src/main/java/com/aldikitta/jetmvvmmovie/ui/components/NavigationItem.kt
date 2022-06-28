package com.aldikitta.jetmvvmmovie.ui.components


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen

sealed class NavigationItem(
    val route: String,
    val icon: @Composable () -> Unit,
    val title: String,
) {
    object Home : NavigationItem(
        NavigationScreen.HOME, {
            Icon(
                Icons.Filled.Home,
                contentDescription = "Home",
            )
        }, "Home"
    )

    object Login : NavigationItem(
        NavigationScreen.LOGIN, {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Genre",
            )
        }, "Genre"
    )

    object Popular : NavigationItem(
        NavigationScreen.POPULAR,
        {
            Icon(
                Icons.Filled.TrendingUp,
                contentDescription = "Popular",
            )
        },
        "Popular",
    )

    object TopRated : NavigationItem(
        NavigationScreen.TOP_RATED,
        {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Top Rated"
            )
        },
        "Top Rated",
    )

    object UpComing : NavigationItem(
        NavigationScreen.UP_COMING,
        {
            Icon(
                Icons.Filled.Upcoming,
                contentDescription = "Up Coming"
            )
        },
        "Up Coming",
    )
}