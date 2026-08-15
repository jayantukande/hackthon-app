package com.signbridgecommunication.app.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Favorites : Screen("favorites")
    object Categories : Screen("categories")
}
