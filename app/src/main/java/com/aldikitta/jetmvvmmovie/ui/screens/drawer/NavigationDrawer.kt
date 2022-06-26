package com.aldikitta.jetmvvmmovie.ui.screens.drawer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.navigation.DrawerNavItems
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre.standardQuadFromTo

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
        BoxWithConstraints(
            modifier = Modifier
                .padding(7.5.dp)
                .height(200.dp)
//                .aspectRatio(0.5f)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.inversePrimary)
        ) {
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            // Medium colored path
            val mediumColoredPoint1 = Offset(0f, height * 0.3f)
            val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
            val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
            val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

            val mediumColoredPath = Path().apply {
                moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
                standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
                standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
                standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
                standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }

            // Light colored path
            val lightPoint1 = Offset(0f, height * 0.35f)
            val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightPoint4 = Offset(width * 0.65f, height.toFloat())
            val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

            val lightColoredPath = Path().apply {
                moveTo(lightPoint1.x, lightPoint1.y)
                standardQuadFromTo(lightPoint1, lightPoint2)
                standardQuadFromTo(lightPoint2, lightPoint3)
                standardQuadFromTo(lightPoint3, lightPoint4)
                standardQuadFromTo(lightPoint4, lightPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }
            val primaryContainer = MaterialTheme.colorScheme.inversePrimary
            val surfaceVariant = MaterialTheme.colorScheme.primaryContainer

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                drawPath(
                    path = mediumColoredPath,
                    color = primaryContainer
                )
                drawPath(
                    path = lightColoredPath,
                    color = surfaceVariant
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Text(
                    text = "BANNER",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.Center),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

            }
        }
        LazyColumn() {
            items(items = items) {item ->
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
//                DrawerItem2(
//                    item = item,
//                    selected = item == selectedItem.value,
//                    onItemClick = {
//                        navController.navigate(NavigationScreen.NavItem)
////                        navController.navigate(NavigationScreen.NavItem.PROFILE) {
////                            launchSingleTop = true
////                        }
//                        selectedItem.value = item
//                        navClose(it.label)
//                    }
//                )
            }
        }
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DrawerItem2(item: List<NavItem>, selected: Boolean, onItemClick: (NavItem) -> Unit) {
//    NavigationDrawerItem(
//        icon = {
//            Icon(imageVector = item.icon, contentDescription = null)
//        },
//        label = {
//            Text(text = item.label)
//        },
//        selected = selected,
//        onClick = { onItemClick(item) },
//        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//
//    )
//}