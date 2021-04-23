package com.example.teaceremony.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teaceremony.database.Repository
import com.example.teaceremony.entity.IngredientsEntity
import kotlinx.coroutines.launch

class IngredientsViewModel(private val repository: Repository) : ViewModel() {

    val ingredients = MutableLiveData<List<IngredientsEntity>>()

    init {
        viewModelScope.launch {
            ingredients.postValue(repository.getAllIngredients())
        }
    }

}

class IngredientsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngredientsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}