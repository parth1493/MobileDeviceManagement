package com.parthdesai.mobiledevicemanagement.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.parthdesai.mobiledevicemanagement.presentation.components.ConnectivityMonitor
import com.parthdesai.mobiledevicemanagement.presentation.components.DeviceList
import com.parthdesai.mobiledevicemanagement.presentation.components.topbar.MainAppBar
import com.parthdesai.mobiledevicemanagement.presentation.components.topbar.SearchWidgetState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(
    isNetworkAvailable: Boolean,
    openDrawer: () -> Unit,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    val devices = homeViewModel.devices.value
    val searchWidgetState by homeViewModel.searchWidgetState
    val searchTextState by homeViewModel.searchTextState
    val loading = homeViewModel.loading.value
    val errorView = homeViewModel.errors.value

    Column(modifier = Modifier.fillMaxSize()) {
        ConnectivityMonitor(isNetworkAvailable = isNetworkAvailable)
        MainAppBar(
            title = "Home Screen",
            navigationIconClicked = openDrawer,
            searchWidgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = {
                homeViewModel.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                homeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
            },
            onSearchClicked = {
                Timber.d("Searched Text $it")
                homeViewModel.updateDeviceList(newValue = it)
            },
            onSearchTriggered = {
                homeViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
            }
        )
        DeviceList(
            loading = loading,
            errorView = errorView,
            devices = devices,
            navController = navController,
            scaffoldState = scaffoldState
        )
    }
}

