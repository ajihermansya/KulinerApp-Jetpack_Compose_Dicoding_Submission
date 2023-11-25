package com.example.kulinerapp.ui.ui_navigations

sealed class ScreenNavigation(val route: String) {
    object Home : ScreenNavigation("home")
    object Favorite : ScreenNavigation("favorite")
    object Profile : ScreenNavigation("profile")
    object Detail : ScreenNavigation("home/{kulinerId}") {
        fun createRoute(kulinerId: Int) = "home/$kulinerId"
    }
}
