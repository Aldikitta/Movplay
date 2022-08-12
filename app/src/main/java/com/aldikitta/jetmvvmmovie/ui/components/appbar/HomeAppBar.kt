package com.aldikitta.jetmvvmmovie.ui.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aldikitta.jetmvvmmovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
//    title: () -> Unit,
    openDrawer: () -> Unit,
    openFilters: () -> Unit,
    onSearchClick: () -> Unit
) {
    SmallTopAppBar(
        title = {
//                title()
//            Text(text = title)
            Image(
                painter = painterResource(id = R.drawable.mov_colored),
                contentDescription = null,
                modifier = Modifier.height(35.dp),
            )
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