package com.github.lucasfodev.astraeon.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Satellites : Screen("satellites")
    object Ocean : Screen("ocean")
    object Alerts : Screen("alerts")
    object Details : Screen("details/{type}/{id}") {
        fun createRoute(type: String, id: Int) = "details/$type/$id"
    }
}
