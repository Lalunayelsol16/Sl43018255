package com.example.appsl43018255.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appsl43018255.data.response.photos.PhotosResponseItem
import com.example.appsl43018255.data.response.todos.TodosResponseItem
import com.example.appsl43018255.data.retrofit.MainRepository
import kotlinx.coroutines.*

class HomeViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<TodosResponseItem>>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getPhotos() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getTodos()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    movieList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}