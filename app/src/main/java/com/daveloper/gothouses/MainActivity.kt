package com.daveloper.gothouses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daveloper.gothouses.core.rememberAppState
import com.daveloper.gothouses.presentation.Screen
import com.daveloper.gothouses.presentation.detailscreen.DetailScreen
import com.daveloper.gothouses.presentation.listscreen.ListScreen
import com.daveloper.gothouses.ui.theme.GoTHousesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoTHousesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val appState = rememberAppState()
                    NavHost(
                        navController = appState.navController,
                        startDestination = Screen.ListScreen.route
                    ) {

                        composable(Screen.ListScreen.route) {
                            ListScreen(appState)
                        }
                        composable(
                            "${Screen.DetailScreen.route}/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val id = remember {
                                it.arguments?.getInt("id") ?: -1
                            }
                            DetailScreen(
                                appState
                            )
                        }
                    }
                }
            }
        }
    }
}