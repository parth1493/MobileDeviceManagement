package com.parthdesai.mobiledevicemanagement.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.presentation.ui.devices.DevicesScreen
import com.parthdesai.mobiledevicemanagement.presentation.ui.home.HomeScreen
import com.parthdesai.mobiledevicemanagement.presentation.ui.devices_detail.DevicesDetail
import com.parthdesai.mobiledevicemanagement.presentation.ui.home.HomeViewModel
import com.parthdesai.mobiledevicemanagement.presentation.ui.settings.SettingsScreen
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    isNetworkAvailable: Boolean,
    navController: NavHostController,
    openDrawer: () -> Unit,
    scaffoldState: ScaffoldState
) {
    NavHost(navController, startDestination = Screens.Home.route) {

        composable(Screens.Home.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(
                isNetworkAvailable = isNetworkAvailable,
                openDrawer = openDrawer,
                homeViewModel = homeViewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }

        composable(Screens.Devices.route) {
            DevicesScreen(openDrawer)
        }

        composable(Screens.Settings.route) {
            SettingsScreen(openDrawer)
        }

        composable(Screens.DeviceDetail.route) {
            val detail = navController.previousBackStackEntry?.savedStateHandle?.get<Device>("device_detail")

            detail?.let {
                DevicesDetail(
                    navController,
                    detail
                )
            }

        }
    }
}