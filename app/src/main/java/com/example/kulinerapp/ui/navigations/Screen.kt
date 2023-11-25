package com.example.kulinerapp.ui.navigations

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{tourismId}") {
        fun createRoute(tourismId: Int) = "home/$tourismId"
    }
}
