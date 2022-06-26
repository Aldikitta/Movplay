//package com.aldikitta.jetmvvmmovie.ui.screens.drawer
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Face
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
//import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
//import com.aldikitta.jetmvvmmovie.navigation.currentRoute
//
//@Composable
//fun DrawerUI(
//    navController: NavController,
//    genres: List<Genre>,
//    closeDrawer: (genreName: String) -> Unit
//
//) {
//    val selectedItem = remember {
//        mutableStateOf(genres[0])
//    }
//
//    LazyColumn() {
//        items(items = genres) { item ->
//            DrawerItem(
//                item = item,
//                selected = item == selectedItem.value,
//                onItemClick = {
//                    navController.navigate(NavigationScreen.NAVIGATION_GENRE.plus("/${it.id}")) {
//                        launchSingleTop = true
//                    }
//                    selectedItem.value = item
//                    closeDrawer(it.name)
//                }
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerItem(item: Genre, selected: Boolean, onItemClick: (Genre) -> Unit) {
//    NavigationDrawerItem(
//        icon = {
//            Icon(imageVector = Icons.Filled.Face, contentDescription = null)
//        },
//        label = {
//            Text(text = item.name)
//        },
//        selected = selected,
//        onClick = { onItemClick(item) },
//        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//
//    )
//}
