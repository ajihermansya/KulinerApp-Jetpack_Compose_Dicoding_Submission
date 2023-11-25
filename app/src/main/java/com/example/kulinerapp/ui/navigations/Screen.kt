package com.example.kulinerapp.ui.navigations

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{kulinerId}") {
        fun createRoute(kulinerId: Int) = "home/$kulinerId"
    }
}
