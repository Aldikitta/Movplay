package com.aldikitta.jetmvvmmovie.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

//val NavItems = listOf(
//    NavItem(
//        label = "Profile",
//        icon = Icons.Filled.Person
//        ),
//    NavItem(
//        label = "Settings",
//        icon = Icons.Filled.Settings
//    )
//)
