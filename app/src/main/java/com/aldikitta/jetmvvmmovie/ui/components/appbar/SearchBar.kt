package com.aldikitta.jetmvvmmovie.ui.components.appbar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aldikitta.jetmvvmmovie.ui.screens.mainscreen.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(FlowPreview::class)
@ExperimentalCoroutinesApi
@Composable
fun SearchBar(
    isAppBarVisible: MutableState<Boolean>,
    viewModel: MainViewModel
) {
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    BackHandler(isAppBarVisible.value.not()) {
        isAppBarVisible.value = true
    }
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                text = it
                viewModel.searchApi(it)
            },
            //shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = {
                if (text.trim().isNotEmpty()) {
                    IconButton(onClick = {
                        text = ""

                    }) {
                        Icon(Icons.Filled.Clear, contentDescription = "abort")

                    }
                } else {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "abort")

                    }
                }
            },
            placeholder = {
                Text(text = "Search Movie")
            },
            leadingIcon = {
                IconButton(onClick = {
                    isAppBarVisible.value = true
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "abort")

                }
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.background)
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }

}