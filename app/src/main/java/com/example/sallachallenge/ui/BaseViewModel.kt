package com.example.sallachallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    protected fun handleError(errorMessage: String?){
        _error.value = errorMessage
    }
}