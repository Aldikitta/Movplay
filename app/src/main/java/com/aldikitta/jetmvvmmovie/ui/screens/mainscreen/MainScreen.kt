package com.aldikitta.jetmvvmmovie.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aldikitta.jetmvvmmovie.R
import com.aldikitta.jetmvvmmovie.data.model.Genres
import com.aldikitta.jetmvvmmovie.data.model.moviedetail.Genre
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
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.DrawerUI
import com.aldikitta.jetmvvmmovie.utils.network.DataState
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
    val genreName = remember {
        mutableStateOf("")
    }
    //genre list for navigation drawer
    val genres = mainScreenViewModel.genres.value
    //internet connection
    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Available
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val itemsDrawer = listOf<Genre>()

    //genre api call for first time
    LaunchedEffect(true) {
        mainScreenViewModel.genreList()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (genres is DataState.Success<Genres>) {
                DrawerUI(navController, genres.data.genres) {
                    scope.launch {
                        drawerState.close()
                    }
                    genreName.value = it
                }
            }
        }) {
        Scaffold(
            topBar = {
                when (currentRoute(navController)) {
                    NavigationScreen.HOME,
                    NavigationScreen.LOGIN,
                    NavigationScreen.POPULAR,
                    NavigationScreen.TOP_RATED,
                    NavigationScreen.UP_COMING,
                    NavigationScreen.NAVIGATION_DRAWER -> {
                        if (isAppBarVisible.value) {
                            val appTitle: String =
                                if (currentRoute(navController) == NavigationScreen.NAVIGATION_DRAWER)
                                    genreName.value
                                else
                                    stringResource(R.string.app_title)
                            HomeAppBar(
                                title = appTitle,
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
//            floatingActionButton = {
//                when (currentRoute(navController)) {
//                    NavigationScreen.HOME,NavigationScreen.LOGIN, NavigationScreen.POPULAR, NavigationScreen.TOP_RATED, NavigationScreen.UP_COMING -> {
//                        FloatingActionButton(
//                            onClick = {
//                                isAppBarVisible.value = false
//                            },
//                        ) {
//                            Icon(Icons.Filled.Search, "", tint = Color.White)
//                        }
//                    }
//                }
//            },
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
                    CircularIndeterminateProgressBar(isDisplayed = searchProgressBar.value, verticalBias = 0.1f)
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
    NavigationBar() {
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
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}