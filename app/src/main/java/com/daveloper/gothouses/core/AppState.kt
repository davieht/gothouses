package com.daveloper.gothouses.core

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val resources: Resources
)

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    resources: Resources = LocalContext.current.resources
) = remember(navController, scaffoldState, resources) {
    AppState(scaffoldState, navController, resources)
}