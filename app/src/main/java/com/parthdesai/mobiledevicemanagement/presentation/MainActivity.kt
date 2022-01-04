package com.parthdesai.mobiledevicemanagement.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.parthdesai.mobiledevicemanagement.BuildConfig
import com.parthdesai.mobiledevicemanagement.R
import dagger.hilt.android.AndroidEntryPoint
import com.parthdesai.mobiledevicemanagement.presentation.navigation.SetupNavGraph
import com.parthdesai.mobiledevicemanagement.presentation.components.Drawer
import com.parthdesai.mobiledevicemanagement.presentation.util.ConnectivityManager
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setContent {
            SetupScreens()
        }
    }

    @ExperimentalMaterialApi
    @Composable
    fun SetupScreens() {

        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()

        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                Drawer(
                    scope = scope,
                    scaffoldState = scaffoldState,
                    navController = navController
                )
            }
        ) {
            SetupNavGraph(
                navController = navController,
                isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                openDrawer = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                scaffoldState = scaffoldState
            )
        }
    }
}