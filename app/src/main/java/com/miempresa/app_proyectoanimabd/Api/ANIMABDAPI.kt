package com.miempresa.app_proyectoanimabd.Api

import com.miempresa.app_proyectoanimabd.Entidades.Cliente
import com.miempresa.app_proyectoanimabd.Entidades.Consultor
import com.miempresa.app_proyectoanimabd.Entidades.Detalle
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.Entidades.Producto
import com.miempresa.app_proyectoanimabd.Entidades.Venta
import com.miempresa.app_proyectoanimabd.Recursos.Especialidad
import com.miempresa.app_proyectoanimabd.Recursos.TipoCliente
import com.miempresa.app_proyectoanimabd.Recursos.TipoVenta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ANIMABDAPI {

    //EMPLEADO
    @GET("GetEmpleado")
    suspend fun GetEmpleado(): Response<List<Empleado>>

    @GET("GetEmpleadoFiltro/{dato}")
    suspend fun GetEmpleadoFiltro(@Path("dato") dato:String): Response<List<Empleado>>

    @POST("PostEmpleado")
    suspend fun PostEmpleado(@Body obj:Empleado): Response<Empleado>

    @PUT("PutEmpleado")
    suspend fun PutEmpleado(@Body obj: Empleado): Response<Empleado>

    @POST("DeleteEmpleado")
    suspend fun DeleteEmpleado(@Body obj:Empleado): Response<Empleado>

    //CONSULTOR
    @GET("GetConsultor")
    suspend fun GetConsultor(): Response<List<Consultor>>

    @GET("GetEspecialidad")
    suspend fun GetEspecialidad(): Response<List<Especialidad>>

    @GET("GetConsultorFiltro/{dato}")
    suspend fun GetConsultorFiltro(@Path("dato") dato:String): Response<List<Consultor>>

    @POST("PostConsultor")
    suspend fun PostConsultor(@Body obj:Consultor): Response<Consultor>

    @PUT("PutConsultor")
    suspend fun PutConsultor(@Body obj: Consultor): Response<Consultor>

    @POST("DeleteConsultor")
    suspend fun DeleteConsultor(@Body obj:Consultor): Response<Consultor>

    //CLIENTE
    @GET("GetCliente")
    suspend fun GetCliente(): Response<List<Cliente>>

    @GET("GetTipoCliente")
    suspend fun GetTipoCliente(): Response<List<TipoCliente>>

    @GET("GetClienteFiltro/{dato}")
    suspend fun GetClienteFiltro(@Path("dato") dato:String): Response<List<Cliente>>

    @POST("PostCliente")
    suspend fun PostCliente(@Body obj:Cliente): Response<Cliente>

    @PUT("PutCliente")
    suspend fun PutCliente(@Body obj: Cliente): Response<Cliente>

    @POST("DeleteCliente")
    suspend fun DeleteCliente(@Body obj:Cliente): Response<Cliente>

    //PRODUCTO
    @GET("GetProducto")
    suspend fun GetProducto(): Response<List<Producto>>

    @GET("GetTipoProducto")
    suspend fun GetTipoProducto(): Response<List<TipoCliente>>

    @GET("GetProductoFiltro/{dato}")
    suspend fun GetProductoFiltro(@Path("dato") dato:String): Response<List<Producto>>

    @POST("PostProducto")
    suspend fun PostProducto(@Body obj:Producto): Response<Producto>

    @PUT("PutProducto")
    suspend fun PutProducto(@Body obj: Producto): Response<Producto>

    @POST("DeleteProducto")
    suspend fun DeleteProducto(@Body obj:Producto): Response<Producto>

    //VENTA
    @GET("GetVenta")
    suspend fun GetVenta(): Response<List<Venta>>

    @GET("GetTipoVenta")
    suspend fun GetTipoVenta(): Response<List<TipoVenta>>

    @GET("GetVentaFiltro/{dato}")
    suspend fun GetVentaFiltro(@Path("dato") dato:String): Response<List<Venta>>

    @POST("PostVenta")
    suspend fun PostVenta(@Body obj:Venta): Response<Venta>

    @POST("DeleteVenta")
    suspend fun DeleteProducto(@Body obj:Venta): Response<Venta>

    //DETALLE
    @GET("GetDetalleVenta")
    suspend fun GetDetalleVenta(): Response<List<Detalle>>

    @GET("GetDetalleVentaFiltro/{dato}")
    suspend fun GetDetalleVentaFiltro(@Path("dato") dato:String): Response<List<Detalle>>

    @POST("PostVenta")
    suspend fun PostVenta(@Body obj:Detalle): Response<Detalle>

}