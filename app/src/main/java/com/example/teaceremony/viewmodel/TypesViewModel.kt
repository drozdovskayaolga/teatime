package com.example.teaceremony.viewmodel

import androidx.lifecycle.*
import com.example.teaceremony.entity.TypesEntity
import com.example.teaceremony.database.Repository
import kotlinx.coroutines.launch

class TypesViewModel(private val repository: Repository) : ViewModel() {

    val allTypes = MutableLiveData<List<TypesEntity>>()

    init {
        viewModelScope.launch {
            allTypes.postValue(repository.getAllTypes())
        }
    }
}

class TypesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TypesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TypesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}