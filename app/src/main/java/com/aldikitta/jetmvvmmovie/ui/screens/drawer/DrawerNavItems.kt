package com.aldikitta.jetmvvmmovie.ui.screens.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.aldikitta.jetmvvmmovie.data.model.NavItem
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen

val DrawerNavItems = listOf(
    NavItem(
        label = NavigationScreen.NavItem.PROFILE,
        icon = Icons.Filled.Person,
        route = "profile"
    ),
//    NavItem(
//        label = Screen.Profile.title,
//        icon = Icons.Filled.Person,
//        route = Screen.Profile.route
//    ),
//    NavItem(
//        label = Screen.Settings.title,
//        icon = Icons.Filled.Settings,
//        route = Screen.Settings.route
//    ),
)