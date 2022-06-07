package com.daveloper.gothouses.presentation.detailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.daveloper.gothouses.core.AppState
import com.daveloper.gothouses.core.ShowSnackbar
import com.daveloper.gothouses.data.Resource
import com.daveloper.gothouses.domain.model.House
import com.daveloper.gothouses.presentation.ResourceState
import com.daveloper.gothouses.ui.theme.Dimensions

@Composable
fun DetailScreen(
    appState: AppState,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val title = if (!state.isLoading) state.item?.name ?: "No Data" else "Loading ..."
    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = title)
            })
        }
    ) { paddingValues ->

        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.isError) {
                ShowSnackbar(
                    coroutineScope = coroutineScope,
                    scaffoldState = appState.scaffoldState,
                    message = "Could not load House"
                )
            } else {
                DetailView(state = viewModel.state)
            }
        }
    }
}

@Composable
fun DetailView(
    state: ResourceState<House>,
) {
    Column(modifier = Modifier.padding(Dimensions.PADDING).fillMaxSize()) {
        state.value.item?.let { house ->
            Text(text = "Name: ${house.name}")
            Text(text = "Lord: ${house.currentLord}")
            Text(text = "Founder: ${house.founder}")
            Text(text = "Region: ${house.region}")
            Text(text = "Overlord: ${house.overlord}")
            Text(text = "Titles: ${house.titles.joinToString(separator = ", ")}")
            Text(text = "Seats: ${house.seats.joinToString(separator = ", ")}")
            Text(text = "Coat of arms: ${house.coatOfArms}")
        } ?: Text("Nothing to show")
    }
}