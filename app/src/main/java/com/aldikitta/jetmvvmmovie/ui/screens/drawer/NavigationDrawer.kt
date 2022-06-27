package com.aldikitta.jetmvvmmovie.ui.screens.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.R
import com.aldikitta.jetmvvmmovie.navigation.DrawerNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerCopy(
    navController: NavController,
    onDestinationClicked: (route: String) -> Unit,
) {
    val items = DrawerNavItems
    val selectedItem = remember {
        mutableStateOf(items[0])
    }
    Column {
        Box(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 50.dp, start = 30.dp, end = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mov_colored),
                contentDescription = null,
            )
        }
        LazyColumn {
            items(items = items) { item ->
                NavigationDrawerItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.label) },
                    selected = item == selectedItem.value,
                    onClick = {
                        onDestinationClicked(item.route)
                        selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    }
}