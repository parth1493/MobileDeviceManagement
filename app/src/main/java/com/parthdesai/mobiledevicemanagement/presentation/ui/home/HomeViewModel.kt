package com.parthdesai.mobiledevicemanagement.presentation.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.interactors.FetchDevices
import com.parthdesai.mobiledevicemanagement.interactors.SearchDevices
import com.parthdesai.mobiledevicemanagement.presentation.components.topbar.SearchWidgetState
import com.parthdesai.mobiledevicemanagement.presentation.util.ConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val fetchDevices: FetchDevices,
    private val connectivityManager: ConnectivityManager,
    private val searchDevices: SearchDevices
) : ViewModel() {

    val devices: MutableState<List<Device>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    val errors = mutableStateOf(false)

    init {
        deviceList()
    }

    private fun deviceList() {

        fetchDevices.execute(
            connectivityManager.isNetworkAvailable.value
        ).onEach { dataSate ->
            loading.value = dataSate.loading

            dataSate.data?.let {
                devices.value = it
            }

            dataSate.error?.let { error ->
                errors.value = true
            }
        }.launchIn(viewModelScope)
    }

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue

        if (devices.value.isEmpty()){
            deviceList()
        }
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun updateDeviceList(newValue: String) {
        _searchTextState.value = newValue

        searchDevices.execute(
            newValue
        ).onEach { dataSate ->
            loading.value = dataSate.loading

            dataSate.data?.let {
                devices.value = it
            }

            dataSate.error?.let { error ->
                Timber.e("Fetch data error")
            }
        }.launchIn(viewModelScope)
    }
}