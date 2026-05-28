package com.example.dailyjournalpic

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {

        composable("dashboard") {
            DashboardScreen(navController)
        }

        composable("camera") {
            CameraScreen(navController)
        }

        composable(
            "review/{imagePath}"
        ) { backStackEntry ->

            val imagePath =
                Uri.decode(
                    backStackEntry.arguments
                        ?.getString("imagePath")
                )

            ReviewScreen(
                navController,
                imagePath!!,
                viewModel = viewModel()
            )
        }
    }
}