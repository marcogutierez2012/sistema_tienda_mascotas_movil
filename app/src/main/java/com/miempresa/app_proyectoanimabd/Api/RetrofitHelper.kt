package com.miempresa.app_proyectoanimabd.Api

import com.miempresa.app_proyectoanimabd.Utiles.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{

        var retrofitEmpleado = Retrofit.Builder()
            .baseUrl(Constantes.RutaEmpleado)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitConsultor = Retrofit.Builder()
            .baseUrl(Constantes.RutaConsultor)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitCliente = Retrofit.Builder()
            .baseUrl(Constantes.RutaCliente)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitProducto = Retrofit.Builder()
            .baseUrl(Constantes.RutaProducto)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitVenta = Retrofit.Builder()
            .baseUrl(Constantes.RutaVenta)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}