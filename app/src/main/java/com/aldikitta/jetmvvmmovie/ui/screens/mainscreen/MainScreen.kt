package com.aldikitta.jetmvvmmovie.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aldikitta.jetmvvmmovie.navigation.Navigation
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.navigation.currentRoute
import com.aldikitta.jetmvvmmovie.navigation.navigationTitle
import com.aldikitta.jetmvvmmovie.ui.components.CircularIndeterminateProgressBar
import com.aldikitta.jetmvvmmovie.ui.components.NavigationItem
import com.aldikitta.jetmvvmmovie.ui.components.SearchUI
import com.aldikitta.jetmvvmmovie.ui.components.appbar.AppBarWithArrow
import com.aldikitta.jetmvvmmovie.ui.components.appbar.HomeAppBar
import com.aldikitta.jetmvvmmovie.ui.components.appbar.SearchBar
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.DrawerCopy
import com.aldikitta.jetmvvmmovie.utils.networkconnection.ConnectionState
import com.aldikitta.jetmvvmmovie.utils.networkconnection.connectivityState
import com.aldikitta.jetmvvmmovie.utils.pagingLoadingState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen() {
    val mainScreenViewModel = hiltViewModel<MainViewModel>()
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember {
        mutableStateOf(true)
    }
    val searchProgressBar = remember {
        mutableStateOf(false)
    }
    val navItemName = remember {
        mutableStateOf("")
    }
    //internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    //genre api call for first time
    LaunchedEffect(true) {
        mainScreenViewModel.genreList()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerCopy(navController) {
                scope.launch {
                    drawerState.close()
                }
                navController.navigate(it) {
                    launchSingleTop = true
                }
                navItemName.value = it
            }
        }
    ) {
        Scaffold(
            topBar = {
                when (currentRoute(navController)) {
                    NavigationScreen.HOME,
                    NavigationScreen.LOGIN,
                    NavigationScreen.POPULAR,
                    NavigationScreen.TOP_RATED,
                    NavigationScreen.UP_COMING
                    -> {
                        if (isAppBarVisible.value) {
//                            val appTitle: String =
//                                if (currentRoute(navController) == NavigationScreen.LOGIN) {
//                                    "Genre"
//                                } else if (currentRoute(navController) == NavigationScreen.POPULAR) {
//                                    "Popular"
//                                } else if (currentRoute(navController) == NavigationScreen.TOP_RATED) {
//                                    "Top Rated"
//                                } else if (currentRoute(navController) == NavigationScreen.UP_COMING) {
//                                    "Up Coming"
//                                } else if (currentRoute(navController) == NavigationScreen.HOME) {
//                                    "Explore"
//                                } else
//                                    ""
                            HomeAppBar(
                                openDrawer = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                },
                                openFilters = {
                                    isAppBarVisible.value = false
                                },
                                onSearchClick = {
                                    isAppBarVisible.value = false
                                }
                            )
                        } else {
                            SearchBar(isAppBarVisible, mainScreenViewModel)
                        }
                    }
                    else -> {
                        AppBarWithArrow(navigationTitle(navController)) {
                            navController.popBackStack()
                        }
                    }
                }
            },
            bottomBar = {
                when (currentRoute(navController)) {
                    NavigationScreen.HOME,
                    NavigationScreen.LOGIN,
                    NavigationScreen.POPULAR,
                    NavigationScreen.TOP_RATED,
                    NavigationScreen.UP_COMING -> {
                        BottomNavigationUI(navController)
                    }
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController, modifier = Modifier)
                Column {
                    CircularIndeterminateProgressBar(
                        isDisplayed = searchProgressBar.value,
                        verticalBias = 0.1f
                    )
                    if (isAppBarVisible.value.not()) {
                        SearchUI(navController, mainScreenViewModel.searchData) {
                            isAppBarVisible.value = true
                        }
                    }
                }
            }
            mainScreenViewModel.searchData.pagingLoadingState {
                searchProgressBar.value = it
            }

        }
    }
}

@Composable
fun BottomNavigationUI(navController: NavController) {
    NavigationBar {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Login,
            NavigationItem.Popular,
            NavigationItem.TopRated,
            NavigationItem.UpComing
        )
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(text = item.title) },
                selected = currentRoute(navController) == item.route,
                icon = item.icon,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reelecting the same item
                        launchSingleTop = true
                        // Restore state when reelecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}