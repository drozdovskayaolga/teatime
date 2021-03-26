package com.example.teaceremony.viewmodel

import androidx.lifecycle.*
import com.example.teaceremony.database.Repository
import com.example.teaceremony.entity.DetailsEntity
import kotlinx.coroutines.launch

class DetailsListViewModel(private val repository: Repository, private val type: Int) :
    ViewModel() {

    val detailsList = MutableLiveData<List<DetailsEntity>>()

    init {
        viewModelScope.launch {
            detailsList.postValue(repository.getDetails(typeId = type))
        }
    }

    fun loadDetailsByIngredients(ingredientsIds: List<Int>) = viewModelScope.launch {
        detailsList.postValue(repository.getDrinksByIngredients(ingredientsIds))
    }
}

class DetailsListViewModelFactory(private val repository: Repository, private val type: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsListViewModel(repository, type) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}