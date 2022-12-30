package com.example.appsl43018255.data.retrofit

import com.example.appsl43018255.data.response.photos.PhotosResponseItem
import com.example.appsl43018255.data.response.todos.TodosResponseItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CallApi {

    @GET("todos")
    suspend fun getTodos() : Response<List<TodosResponseItem>>

    @GET("photos")
    suspend fun getPhotos() : Response<List<PhotosResponseItem>>

    companion object {
        var retrofitService: CallApi? = null
        fun getInstance() : CallApi {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(CallApi::class.java)
            }
            return retrofitService!!
        }

    }

}