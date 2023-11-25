package com.example.kulinerapp.home.unit_app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kulinerapp.home.navigation_fragment.ScreenDetail
import com.example.kulinerapp.home.navigation_fragment.ScreenFavorite
import com.example.kulinerapp.home.navigation_fragment.ScreenHome
import com.example.kulinerapp.home.navigation_fragment.ScreenProfile
import com.example.kulinerapp.ui.ui_navigations.ItemNavigation
import com.example.kulinerapp.ui.ui_navigations.ScreenNavigation

@Composable

fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != ScreenNavigation.Detail.route) {
                BottomBar(navController, currentRoute)
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(snackbarData = data, shape = RoundedCornerShape(8.dp))
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Home.route) {
                ScreenHome(navController, scaffoldState)
            }
            composable(
                route = ScreenNavigation.Detail.route,
                arguments = listOf(
                    navArgument("kulinerId") { type = NavType.IntType }
                )
            ) {
                val kulinerId = it.arguments?.getInt("kulinerId") ?: 0
                ScreenDetail(kulinerId, navController, scaffoldState)
            }
            composable(ScreenNavigation.Favorite.route) {
                ScreenFavorite(navController, scaffoldState)
            }
            composable(ScreenNavigation.Profile.route) {
                ScreenProfile()
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
) {
    val itemNavigations = listOf(
        ItemNavigation(
            title = "Home",
            icon = Icons.Rounded.Home,
            screenNavigation = ScreenNavigation.Home
        ),
        ItemNavigation(
            title = "Favorite",
            icon = Icons.Rounded.Favorite,
            screenNavigation = ScreenNavigation.Favorite
        ),
        ItemNavigation(
            title = "Profile",
            icon = Icons.Rounded.Person,
            screenNavigation = ScreenNavigation.Profile
        ),
    )

    BottomNavigation(backgroundColor = Color.White) {
        itemNavigations.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screenNavigation.route,
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.Black,
                onClick = {
                    navController.navigate(item.screenNavigation.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}