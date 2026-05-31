package com.github.lucasfodev.astraeon.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.lucasfodev.astraeon.ui.screens.*
import com.github.lucasfodev.astraeon.viewmodel.*

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = { fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
        exitTransition = { fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500)) },
        popEnterTransition = { fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500)) },
        popExitTransition = { fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Home.route) {
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        composable(Screen.Satellites.route) {
            val viewModel: SatelliteViewModel = viewModel()
            SatelliteScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNavigateToDetails = { id -> 
                    navController.navigate(Screen.Details.createRoute("satellite", id)) 
                }
            )
        }

        composable(Screen.Ocean.route) {
            val viewModel: OceanViewModel = viewModel()
            OceanScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNavigateToDetails = { id -> 
                    navController.navigate(Screen.Details.createRoute("ocean", id)) 
                }
            )
        }

        composable(Screen.Alerts.route) {
            val viewModel: AlertViewModel = viewModel()
            AlertScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNavigateToDetails = { id -> 
                    navController.navigate(Screen.Details.createRoute("alert", id)) 
                }
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            
            DetailsScreen(
                type = type,
                id = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
