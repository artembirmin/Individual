package com.example.individual.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val messageByToastLiveData: MutableLiveData<String> = MutableLiveData()

    fun showLoader() {
        setLoading(true)
    }

    fun hideLoader() {
        setLoading(false)
    }

    fun setLoading(isLoading: Boolean) {
        isLoadingLiveData.postValue(isLoading)
    }

    fun showMessage(message: String) {
        messageByToastLiveData.postValue(message)
    }
}