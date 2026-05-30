package com.example.dailyjournalpic

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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

        composable(
            route = "edit/{journalId}",
            arguments = listOf(navArgument("journalId") { type = NavType.IntType })
        ) { backStackEntry ->
            val journalId = backStackEntry.arguments?.getInt("journalId") ?: 0
            EditScreen(journalId, navController, )
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