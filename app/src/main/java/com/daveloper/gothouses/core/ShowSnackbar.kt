package com.daveloper.gothouses.core

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShowSnackbar(coroutineScope: CoroutineScope, scaffoldState: ScaffoldState, message: String) =
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message,
                "OK"
            )

            if (result == SnackbarResult.ActionPerformed) {
                // Do something
            }
        }
    }