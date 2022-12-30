package com.example.appsl43018255.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appsl43018255.data.retrofit.MainRepository
import com.example.appsl43018255.ui.gallery.GalleryViewModel
import com.example.appsl43018255.ui.home.HomeViewModel

class ViewModelFactoryTodos(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}