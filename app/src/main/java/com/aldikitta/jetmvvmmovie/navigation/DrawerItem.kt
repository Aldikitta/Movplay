package com.aldikitta.jetmvvmmovie.navigation

sealed class DrawerItem(val title: String, val route: String) {
    object Profile : DrawerItem("Profile", "profile")
    object Settings : DrawerItem("Settings", "settings")
    object Preferences : DrawerItem("Preferences", "preferences")
    object Help : DrawerItem("Help", "help")

}