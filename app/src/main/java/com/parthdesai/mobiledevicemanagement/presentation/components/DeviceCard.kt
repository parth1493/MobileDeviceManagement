package com.parthdesai.mobiledevicemanagement.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parthdesai.mobiledevicemanagement.domain.model.Device
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.parthdesai.mobiledevicemanagement.utils.DEFAULT_DEVICE_IMAGE
import com.parthdesai.mobiledevicemanagement.utils.loadPicture

@ExperimentalCoroutinesApi
@Composable
fun DeviceCard(
    device: Device,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 6.dp,
                bottom = 10.dp,
                top = 10.dp,
                end = 6.dp
            )
            .border(
                width = 2.dp,
                color = Color.Black
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .height(80.dp)
        ) {
            device.imageURL?.let {
                val image =
                    loadPicture(url = device.imageURL, defaultImage = DEFAULT_DEVICE_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.30f),
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp)
                    .fillMaxWidth(.85f),
                verticalArrangement = Arrangement.Center
            ) {
                device.title?.let {
                    Text(
                        text = device.title,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 30.sp
                        )
                    )
                }
                device.type?.let {
                    Text(
                        text = device.type,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 24.sp
                        )
                    )
                }
            }
            Icon(
                Icons.Default.Info,
                modifier = Modifier
                    .scale(2.5f)
                    .padding(end = 10.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(.95f)
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically),
                contentDescription = null
            )
        }
    }
}
