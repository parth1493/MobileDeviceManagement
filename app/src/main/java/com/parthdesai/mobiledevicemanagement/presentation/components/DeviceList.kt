package com.parthdesai.mobiledevicemanagement.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.presentation.navigation.Screens
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun DeviceList(
    loading: Boolean,
    errorView : Boolean,
    devices: List<Device>,
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (loading && devices.isEmpty()) {
            LoadingDeviceListShimmer(boxHeight = 110.dp)
        } else if (devices.isEmpty()) {
            NothingHere()
        } else if(errorView){
            coroutineScope.launch {
                snackbar(
                    scaffoldState = scaffoldState,
                    "Error",
                    "OK",
                    SnackbarDuration.Indefinite
                )
            }
        }
        else {
            LazyColumn {
                itemsIndexed(
                    items = devices
                ) { _, item ->
                    DeviceCard(
                        device = item,
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "device_detail",
                                item
                            )
                            navController.navigate(Screens.DeviceDetail.route)
                        }
                    )
                }
            }
        }
    }
}