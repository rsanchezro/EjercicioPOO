package com.example.ejerciciopoo.data

class Administrador(var area:String):Usuario() {
    override fun obtenerDescripcion()="${super.obtenerDescripcion()}-Area:$area"
}