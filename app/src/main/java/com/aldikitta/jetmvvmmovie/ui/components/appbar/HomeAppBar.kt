package com.aldikitta.jetmvvmmovie.ui.components.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeAppBar(
    title: String,
    openDrawer: () -> Unit,
    openFilters: () -> Unit,
    onSearchClick: () -> Unit
) {
    SmallTopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(
                onClick = { onSearchClick() },
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}