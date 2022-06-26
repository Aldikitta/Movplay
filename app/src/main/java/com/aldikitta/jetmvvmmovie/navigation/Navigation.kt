package com.aldikitta.jetmvvmmovie.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.aldikitta.jetmvvmmovie.R
import com.aldikitta.jetmvvmmovie.ui.screens.artistdetail.ArtistDetail
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.nowplaying.NowPlaying
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.popular.Popular
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.toprated.TopRated
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.upcoming.Upcoming
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre.GenreScreen
import com.aldikitta.jetmvvmmovie.ui.screens.bottombar.genre.GenreTestingScreen
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.draweritems.help.Help
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.draweritems.preferences.Preferences
import com.aldikitta.jetmvvmmovie.ui.screens.moviedetail.MovieDetail
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.draweritems.profile.ProfileScreen
import com.aldikitta.jetmvvmmovie.ui.screens.drawer.draweritems.settings.Settings


@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    val genreName = remember {
        mutableStateOf("")
    }

    NavHost(navController, startDestination = "home", modifier) {
        composable(NavigationScreen.HOME) {
            NowPlaying(
                navController = navController,
            )
        }
        composable(NavigationScreen.LOGIN) {
            GenreTestingScreen(
                navController = navController,
            ){
                genreName.value = it
            }
        }
        composable(NavigationScreen.POPULAR) {
            Popular(
                navController = navController
            )
        }
        composable(NavigationScreen.TOP_RATED) {
            TopRated(
                navController = navController
            )
        }
        composable(NavigationScreen.UP_COMING) {
            Upcoming(
                navController = navController
            )
        }
        composable(DrawerItem.Profile.route){
            ProfileScreen(
                navController = navController
            )
        }
        composable(DrawerItem.Help.route){
            Help(
                navController = navController
            )
        }
        composable(DrawerItem.Preferences.route){
            Preferences(
                navController = navController
            )
        }
        composable(DrawerItem.Settings.route){
            Settings(
                navController = navController
            )
        }
        composable(
            NavigationScreen.NAVIGATION_GENRE_WITH_GENRE_ID,
            arguments = listOf(navArgument(NavigationScreen.GENRE_ID) {
                type = NavType.StringType
            })
        ) { backStack ->
            val genreId = backStack.arguments?.getString(NavigationScreen.GENRE_ID)
            genreId?.let {
                GenreScreen(
                    navController = navController,
                    genreId
                )
            }
        }
        composable(
            NavigationScreen.MovieDetail.MOVIE_DETAIL.plus(NavigationScreen.MovieDetail.MOVIE_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationScreen.MovieDetail.MOVIE_ITEM) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId =
                it.arguments?.getInt(NavigationScreen.MovieDetail.MOVIE_ITEM)
            if (movieId != null) {
                MovieDetail(
                    navController = navController,
                    movieId
                )
            }
            /*movieItem?.fromPrettyJson<MovieItem>()
                ?.let { movieObject ->
                    MovieDetail(
                        movieObject
                    )
                }*/
        }
        composable(
            NavigationScreen.ArtistDetail.ARTIST_DETAIL.plus(NavigationScreen.ArtistDetail.ARTIST_DETAIL_PATH),
            arguments = listOf(navArgument(NavigationScreen.ArtistDetail.ARTIST_ID) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.artist_detail)
            val artistId =
                it.arguments?.getInt(NavigationScreen.ArtistDetail.ARTIST_ID)
            if (artistId != null) {
                ArtistDetail(
                    artistId
                )
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        DrawerItem.Profile.route -> "Profile"
        DrawerItem.Settings.route -> "Settings"
        DrawerItem.Preferences.route -> "Preferences"
        DrawerItem.Help.route -> "Help"
        NavigationScreen.MovieDetail.MOVIE_DETAIL -> stringResource(id = R.string.movie_detail)
        NavigationScreen.ArtistDetail.ARTIST_DETAIL -> stringResource(id = R.string.artist_detail)
        NavigationScreen.LOGIN -> stringResource(id = R.string.login)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
