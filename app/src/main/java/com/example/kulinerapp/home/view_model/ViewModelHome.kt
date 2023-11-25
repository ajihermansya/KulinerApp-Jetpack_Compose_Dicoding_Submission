package com.example.kulinerapp.home.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.data.repository.Repository
import com.example.kulinerapp.utils.interface_utils.StateInterface
import com.example.kulinerapp.utils.list_data.DataKuliner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allKuliner = MutableStateFlow<StateInterface<List<KulinerEntity>>>(StateInterface.Loading)
    val allKuliner = _allKuliner.asStateFlow()

    private val _StateHome = mutableStateOf(StateHome())
    val stateHome: State<StateHome> = _StateHome

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllKuliners().collect { kuliner ->
                when (kuliner.isEmpty()) {
                    true -> repository.insertAllKuliners(DataKuliner.kuliners)
                    else -> _allKuliner.value = StateInterface.Success(kuliner)
                }
            }
        }
    }

    private fun searchKuliner(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchKuliners(query)
                .catch { _allKuliner.value = StateInterface.Error(it.message.toString()) }
                .collect { _allKuliner.value = StateInterface.Success(it) }
        }
    }

    fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteKuliners(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _StateHome.value = _StateHome.value.copy(query = query)
        searchKuliner(query)
    }
}

