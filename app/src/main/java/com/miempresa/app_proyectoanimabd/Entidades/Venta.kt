package com.miempresa.app_proyectoanimabd.Entidades

data class Venta(
    var codventa:String,
    var fecventa:String,
    var codtipoven:Int,
    var codcli:String,
    var codemp:String,
    var codconsul:String,
    var total:Double,
    var anulado:String
)
