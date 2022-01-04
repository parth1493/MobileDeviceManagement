package com.parthdesai.mobiledevicemanagement.presentation.ui.devices_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import com.parthdesai.mobiledevicemanagement.presentation.components.topbar.DefaultAppBarWithBackArrow
import com.parthdesai.mobiledevicemanagement.presentation.navigation.Screens
import com.parthdesai.mobiledevicemanagement.utils.DEFAULT_DEVICE_IMAGE
import com.parthdesai.mobiledevicemanagement.utils.loadPicture

const val IMAGE_HEIGHT = 260
@Composable
fun DevicesDetail(
    navController: NavHostController,
    device: Device
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DefaultAppBarWithBackArrow(
            title = "Devices Detail",
            onBackPress = {
                navController.navigate(Screens.Home.route)
            },
            isFavorite = device.isFavorite ?: false
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
        ) {
            device.imageURL?.let {
                val image =
                    loadPicture(url = device.imageURL, defaultImage = DEFAULT_DEVICE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IMAGE_HEIGHT.dp),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                }
            }
            device.title?.let {
                Text(
                    text = "Name $it",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
            device.type?.let {
                Text(
                    text = "OS $it",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
            device.price?.let {
                Text(
                    text = "Price $it",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}