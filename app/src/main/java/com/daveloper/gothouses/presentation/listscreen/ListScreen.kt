package com.daveloper.gothouses.presentation.listscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.daveloper.gothouses.core.AppState
import com.daveloper.gothouses.core.ShowSnackbar
import com.daveloper.gothouses.domain.model.House
import com.daveloper.gothouses.presentation.Screen
import com.daveloper.gothouses.ui.theme.Dimensions

@Composable
fun ListScreen(
    appState: AppState,
    viewModel: ListScreenViewModel = hiltViewModel()
) {
    Scaffold(
        scaffoldState = appState.scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "GoT Houses")
            })
        }
    ) { paddingValues ->
        val state = viewModel.state.value
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
                    message = "Coult not load Houses"
                )
            } else {
                HouseList(
                    list = state.item.orEmpty(),
                    onItemClick = { id ->
                        appState.navController.navigate("${Screen.DetailScreen.route}/$id")
                    })
            }
        }
    }
}

@Composable
fun HouseList(
    list: List<House>,
    onItemClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.PADDING),
        verticalArrangement = Arrangement.spacedBy(Dimensions.PADDING)
    ) {
        items(list) { item ->
            ListItem(item.name, item.region, Modifier.clickable {
                onItemClick(item.id)
            })
        }
    }
}

@Composable
fun ListItem(title: String, subTitle: String, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column {
            Text(text = title)
            Text(
                text = subTitle,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview
@Composable
fun ListItemPreview() {
    ListItem("I dont", "know anything about GoT", Modifier.clickable { })
}