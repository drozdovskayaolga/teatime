package com.example.teaceremony.viewmodel

import androidx.lifecycle.*
import com.example.teaceremony.database.Repository
import com.example.teaceremony.entity.DetailsEntity
import kotlinx.coroutines.launch

class DetailsListViewModel(private val repository: Repository) : ViewModel() {

    val detailsList = MutableLiveData<List<DetailsEntity>>()

    fun typeOfData (typeId: Int) {
        viewModelScope.launch {
            detailsList.postValue(repository.getDetails(typeId = typeId))
        }
    }
}

class DetailsListViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}