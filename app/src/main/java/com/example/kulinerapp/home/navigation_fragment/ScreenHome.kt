package com.example.kulinerapp.home.navigation_fragment

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.home.view_model.ViewModelHome
import com.example.kulinerapp.ui.component.AvailableContent
import com.example.kulinerapp.ui.component.ComponentEmpty
import com.example.kulinerapp.ui.component.ComponentError
import com.example.kulinerapp.ui.component.ComponentLoading
import com.example.kulinerapp.ui.component.ComponentSearchBar
import com.example.kulinerapp.utils.StateInterface

@Composable
fun ScreenHome(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<ViewModelHome>()
    val homeState by homeViewModel.stateHome

    homeViewModel.allKuliner.collectAsState(StateInterface.Loading).value.let { uiState ->
        when (uiState) {
            is StateInterface.Loading -> ComponentLoading()
            is StateInterface.Error -> ComponentError()
            is StateInterface.Success -> {
                HomeContent(
                    listKuliner = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoriteKuliner = homeViewModel::updateFavoriteKuliners
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listKuliner: List<KulinerEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteKuliner: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        ComponentSearchBar(query = query, onQueryChange = onQueryChange)
        when (listKuliner.isEmpty()) {
            true -> ComponentEmpty()
            false -> AvailableContent(listKuliner, navController, scaffoldState, onUpdateFavoriteKuliner)
        }
    }
}

