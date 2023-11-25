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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kulinerapp.home.navigation_fragment.HomeScreen
import com.example.kulinerapp.ui.navigations.ItemNavigation
import com.example.kulinerapp.ui.navigations.Screen

@Composable

fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
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
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController, scaffoldState)
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("kulinerId") { type = NavType.IntType }
                )
            ) {
                val kulinerId = it.arguments?.getInt("kulinerId") ?: 0
               // DetailScreen(kulinerId, navController, scaffoldState)
            }
            composable(Screen.Favorite.route) {
              //  FavoriteScreen(navController, scaffoldState)
            }
            composable(Screen.Profile.route) {
               // ProfileScreen()
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
            screen = Screen.Home
        ),
        ItemNavigation(
            title = "Favorite",
            icon = Icons.Rounded.Favorite,
            screen = Screen.Favorite
        ),
        ItemNavigation(
            title = "Profile",
            icon = Icons.Rounded.Person,
            screen = Screen.Profile
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
                selected = currentRoute == item.screen.route,
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.Gray,
                onClick = {
                    navController.navigate(item.screen.route) {
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