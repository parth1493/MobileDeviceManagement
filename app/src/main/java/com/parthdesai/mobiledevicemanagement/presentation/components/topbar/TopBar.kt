package com.parthdesai.mobiledevicemanagement.presentation.components.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainAppBar(
    title: String = "",
    navigationIconClicked: () -> Unit,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBarWithSearchIcon(
                title,
                navigationIconClicked = navigationIconClicked,
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultAppBarWithSearchIcon(
    title: String = "",
    navigationIconClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title, fontSize = 30.sp) },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked()
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        actions = {
            IconButton(onClick = {
                onSearchClicked()
            }) {
                Icon(Icons.Filled.Search, "")
            }
        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black
    )
}

@Composable
fun DefaultAppBar(
    title: String = "",
    navigationIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title, fontSize = 30.sp) },
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked()
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black
    )
}

@Composable
fun DefaultAppBarWithBackArrow(
    onBackPress: () -> Unit,
    title: String = "",
    isFavorite:Boolean
) {
    TopAppBar(
        title = { Text(text = title, fontSize = 30.sp) },
        navigationIcon = {
            IconButton(onClick = {
                onBackPress()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        actions = {
            if(isFavorite){
                Icon(Icons.Filled.StarRate, "")
            }else{
                Icon(Icons.Outlined.StarRate, "")
            }
        },
        backgroundColor = Color.LightGray,
        contentColor = Color.Black
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.LightGray
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black.copy(alpha = ContentAlpha.high)
            ))
    }
}