package com.example.teaceremony.viewmodel

import androidx.lifecycle.*
import com.example.teaceremony.database.Repository
import com.example.teaceremony.entity.DrinksEntity
import kotlinx.coroutines.launch

class DrinksViewModel(private val repository: Repository, private val type: Int) :
    ViewModel() {

    val drinks = MutableLiveData<List<DrinksEntity>>()

    init {
        viewModelScope.launch {
            drinks.postValue(repository.getDetails(typeId = type))
        }
    }

    fun loadDetailsByIngredients(ingredientsIds: List<Int>) = viewModelScope.launch {
        drinks.postValue(repository.getDrinksByIngredients(ingredientsIds))
    }
}

class DrinksViewModelFactory(private val repository: Repository, private val type: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrinksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DrinksViewModel(repository, type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}