package com.aldikitta.jetmvvmmovie.ui.screens.drawer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.navigation.currentRoute

@Composable
fun DrawerUI(
    navController: NavController,
    genres: List<Genre>,
    closeDrawer: (genreName: String) -> Unit

) {
    LazyColumn() {
        items(items = genres) { item ->
            DrawerItem(
                item = item,
                selected = currentRoute(navController) == "",
                onItemClick = {
                    navController.navigate(NavigationScreen.NAVIGATION_DRAWER.plus("/${it.id}")) {
                        launchSingleTop = true
                    }
                    closeDrawer(it.name)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(item: Genre, selected: Boolean, onItemClick: (Genre) -> Unit) {
    NavigationDrawerItem(
        label = {
                item.name
        },
        selected = selected,
        onClick = { onItemClick(item) })
}
