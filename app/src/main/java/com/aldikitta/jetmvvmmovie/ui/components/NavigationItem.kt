package com.aldikitta.jetmvvmmovie.ui.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
                Icons.Filled.Home,
                contentDescription = "Login",
            )
        }, "Login"
    )

    object Popular : NavigationItem(
        NavigationScreen.POPULAR,
        {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Popular",
            )
        },
        "Popular",
    )

    object TopRated : NavigationItem(
        NavigationScreen.TOP_RATED,
        {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Top Rated"
            )
        },
        "Top Rated",
    )

    object UpComing : NavigationItem(
        NavigationScreen.UP_COMING,
        {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Up Coming"
            )
        },
        "Up Coming",
    )
}