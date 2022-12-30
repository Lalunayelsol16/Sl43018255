package com.example.appsl43018255.data.retrofit

class MainRepository(private val callApi: CallApi) {

    suspend fun getPhotos() = CallApi.retrofitService?.getPhotos()
    suspend fun getTodos() = CallApi.retrofitService?.getTodos()

}