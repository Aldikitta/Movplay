package com.aldikitta.jetmvvmmovie.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.aldikitta.jetmvvmmovie.data.model.NavItem

val DrawerNavItems = listOf(
    NavItem(
        label = DrawerItem.Profile.title,
        icon = Icons.Filled.Person,
        route = DrawerItem.Profile.route
    ),
    NavItem(
        label = DrawerItem.Preferences.title,
        icon = Icons.Filled.Assistant,
        route = DrawerItem.Preferences.route
    ),NavItem(
        label = DrawerItem.Settings.title,
        icon = Icons.Filled.Settings,
        route = DrawerItem.Settings.route
    ),NavItem(
        label = DrawerItem.Help.title,
        icon = Icons.Filled.Help,
        route = DrawerItem.Help.route
    ),
)