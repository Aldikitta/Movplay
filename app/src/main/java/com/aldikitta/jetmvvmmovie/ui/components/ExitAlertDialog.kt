package com.aldikitta.jetmvvmmovie.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.aldikitta.jetmvvmmovie.R
import com.aldikitta.jetmvvmmovie.navigation.NavigationScreen
import com.aldikitta.jetmvvmmovie.navigation.currentRoute

@Composable
fun ExitAlertDialog(
    navController: NavController,
    cancel: (isOpen: Boolean) -> Unit, ok: () -> Unit
) {
    val openDialog = remember {
        mutableStateOf(true)
    }
    if (currentRoute(navController = navController) == NavigationScreen.HOME && openDialog.value) {
        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = stringResource(R.string.close_the_app)) },
            text = {
                Text(text = stringResource(R.string.do_you_want_to_exit_the_app))
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    ok()
                }) {
                    Text(
                        stringResource(R.string.yes)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    cancel(false)
                }) {
                    Text(
                        stringResource(R.string.no)
                    )
                }
            }
        )
    }
}