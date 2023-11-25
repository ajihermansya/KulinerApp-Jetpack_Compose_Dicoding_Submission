package com.example.kulinerapp.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kulinerapp.data.local.KulinerEntity
import com.example.kulinerapp.data.repository.Repository
import com.example.kulinerapp.utils.interface_utils.StateInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDetail @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _kuliner = MutableStateFlow<StateInterface<KulinerEntity>>(StateInterface.Loading)
    val kuliners = _kuliner.asStateFlow()

    fun getKuliner(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getKuliners(id).collect { _kuliner.value = StateInterface.Success(it) }
            } catch (e: Exception) {
                _kuliner.value = StateInterface.Error(e.message.toString())
            }
        }
    }

    fun updateFavoriteKuliners(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteKuliners(id, isFavorite)
        }
    }
}