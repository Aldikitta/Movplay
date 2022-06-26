package com.aldikitta.jetmvvmmovie.ui.screens.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.data.model.NavItem
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.navigation.currentRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerCopy(
    navController: NavController,
//    genres: List<Genre>,
    navClose: (genreName: String) -> Unit,

    ) {
    val items = DrawerNavItems
    val selectedItem = remember {
        mutableStateOf(items[0])
    }
    Column() {
        LazyColumn() {
            items(items = items) { item ->
                DrawerItem2(
                    item = item,
                    selected = item == selectedItem.value,
                    onItemClick = {
                        navController.navigate(NavigationScreen.NavItem.PROFILE) {
                            launchSingleTop = true
                        }
                        selectedItem.value = item
                        navClose(it.label)
                    }
                    )
//                NavigationDrawerItem(
//                    label = {
//                        Text(text = item.label)
//                    },
//                    selected = item == selectedItem.value,
//                    onClick = (Genre){
//                        navController.navigate(NavigationScreen.NavItem.PROFILE){
//                            launchSingleTop = true
//                        }
//
//                        selectedItem.value = item
//                        navClose(it.name)
//                    },
//                    icon = {
//                        Icon(imageVector = item.icon, contentDescription = item.label)
//                    }
//                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem2(item: NavItem, selected: Boolean, onItemClick: (NavItem) -> Unit) {
    NavigationDrawerItem(
        icon = {
            Icon(imageVector = item.icon, contentDescription = null)
        },
        label = {
            Text(text = item.label)
        },
        selected = selected,
        onClick = { onItemClick(item) },
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

    )
}