package com.parthdesai.mobiledevicemanagement.presentation.navigation

import com.parthdesai.mobiledevicemanagement.R

sealed class Screens(var route: String, var icon: Int, var title: String)
{
    object Home : Screens("home", R.drawable.ic_home_24, "Home")
    object Devices : Screens("devices", R.drawable.ic_device_24, "My Devices")
    object Settings : Screens("settings", R.drawable.ic_settings_24, "Settings")
    object DeviceDetail : Screens("device_detail", 0, "DeviceDetail")
}