package com.parthdesai.mobiledevicemanagement.presentation.ui.devices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.parthdesai.mobiledevicemanagement.presentation.components.topbar.DefaultAppBar


@Composable
fun DevicesScreen(
    openDrawer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DefaultAppBar(
            title = "Devices Screen",
            navigationIconClicked = openDrawer
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Devices Screen",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}