package com.aldikitta.jetmvvmmovie.ui.screens.artistdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.aldikitta.jetmvvmmovie.data.datasource.remote.ApiURL
import com.aldikitta.jetmvvmmovie.data.model.artist.ArtistDetail
import com.aldikitta.jetmvvmmovie.ui.components.CircularIndeterminateProgressBar
import com.aldikitta.jetmvvmmovie.utils.genderInString
import com.aldikitta.jetmvvmmovie.utils.network.DataState
import com.aldikitta.jetmvvmmovie.utils.pagingLoadingState

@Composable
fun ArtistDetail(personId: Int) {
    val artistDetailViewModel = hiltViewModel<ArtistDetailViewModel>()
    val artistDetail = artistDetailViewModel.artistDetail
    val progressBar = remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        artistDetailViewModel.artistDetail(personId)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
    ) {
        CircularIndeterminateProgressBar(isDisplayed = progressBar.value, 0.4f)

        artistDetail.value.let {
            if (it is DataState.Success<ArtistDetail>) {
                Row {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.data.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .height(250.dp)
                            .width(190.dp)
                    )
                    Column {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = it.data.name,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Medium
                        )
                        PersonalInfo(
                            stringResource(com.aldikitta.jetmvvmmovie.R.string.know_for),
                            it.data.knownForDepartment
                        )
                        PersonalInfo(
                            stringResource(com.aldikitta.jetmvvmmovie.R.string.gender),
                            it.data.gender.genderInString()
                        )
                        PersonalInfo(
                            stringResource(com.aldikitta.jetmvvmmovie.R.string.birth_day),
                            it.data.birthday
                        )
                        PersonalInfo(
                            stringResource(com.aldikitta.jetmvvmmovie.R.string.place_of_birth),
                            it.data.placeOfBirth
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(com.aldikitta.jetmvvmmovie.R.string.biography),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = it.data.biography
                )
            }
        }
    }

    artistDetail.pagingLoadingState {
        progressBar.value = it
    }
}

@Composable
fun PersonalInfo(title: String, info: String) {
    Column(modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info,
            fontSize = 16.sp
        )
    }
}