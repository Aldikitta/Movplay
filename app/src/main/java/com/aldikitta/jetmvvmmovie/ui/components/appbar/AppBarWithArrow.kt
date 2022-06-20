package com.aldikitta.jetmvvmmovie.ui.components.appbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppBarWithArrow(
    title: String?,
    pressOnBack: () -> Unit
)
//    openFilters: () -> Unit)
{
    SmallTopAppBar(
        title = {
            Text(text = title ?: "")
        },
        navigationIcon = {
            IconButton(onClick = {
                pressOnBack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}