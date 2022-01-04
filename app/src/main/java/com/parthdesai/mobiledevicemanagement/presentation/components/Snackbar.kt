package com.parthdesai.mobiledevicemanagement.presentation.components

import android.util.Log
import androidx.compose.material.*
import timber.log.Timber


suspend fun snackbar(
    scaffoldState: ScaffoldState,
    message: String,
    actionLabel: String,
    duration: SnackbarDuration
) {

    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
        message = message,
        actionLabel = actionLabel,
        duration = duration
    )
    when (snackbarResult) {
        SnackbarResult.Dismissed -> Timber.d("Dismissed")
        SnackbarResult.ActionPerformed -> Timber.d("Snackbar's button clicked")
    }
}