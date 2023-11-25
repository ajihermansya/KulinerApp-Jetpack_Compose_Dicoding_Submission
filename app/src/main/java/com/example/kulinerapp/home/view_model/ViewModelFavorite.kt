package com.example.kulinerapp.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.data.repository.Repository
import com.example.kulinerapp.utils.StateInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFavorite @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allFavoriteKuliners = MutableStateFlow<StateInterface<List<KulinerEntity>>>(StateInterface.Loading)
    val allFavoriteKuliners = _allFavoriteKuliners.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteKuliners()
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun handleError(error: Throwable) {
        _allFavoriteKuliners.value = StateInterface.Error(error.message.toString())
    }

    private fun handleSuccess(data: List<KulinerEntity>) {
        _allFavoriteKuliners.value = StateInterface.Success(data)
    }

    fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteKuliners(id, isFavorite)
        }
    }
}