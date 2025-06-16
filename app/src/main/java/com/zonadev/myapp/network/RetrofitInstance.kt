package com.zonadev.myapp.network

import com.zonadev.myapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
//Apiservice es la interfaz que define los endpoints de la API
//El repositorio(MovieRepositoryImpl) es el intermediario entre el api y el viewmodel
/*Funciones:
- LLamar a la api
- Devolver los datos al ViewModel de forma limpia
*/