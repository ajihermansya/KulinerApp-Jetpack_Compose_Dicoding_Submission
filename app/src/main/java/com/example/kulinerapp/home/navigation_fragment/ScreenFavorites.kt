package com.example.kulinerapp.home.navigation_fragment

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.home.view_model.ViewModelFavorite
import com.example.kulinerapp.ui.ui_components.AvailableContent
import com.example.kulinerapp.ui.ui_components.ComponentEmpty
import com.example.kulinerapp.ui.ui_components.ComponentError
import com.example.kulinerapp.ui.ui_components.ComponentLoading
import com.example.kulinerapp.utils.interface_utils.StateInterface

@Composable
fun ScreenFavorite(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelFavorite = hiltViewModel<ViewModelFavorite>()

    viewModelFavorite.allFavoriteKuliners.collectAsState(StateInterface.Loading).value.let { uiState ->
        when (uiState) {
            is StateInterface.Loading -> ComponentLoading()
            is StateInterface.Error -> ComponentError()
            is StateInterface.Success -> {
                FavoriteContent(
                    listFavoriteKuliners = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteKuliner = viewModelFavorite::updateFavoriteKuliners
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteKuliners: List<KulinerEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteKuliner: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteKuliners.isEmpty()) {
        true -> ComponentEmpty()
        false -> AvailableContent(listFavoriteKuliners, navController, scaffoldState, onUpdateFavoriteKuliner)
    }
}