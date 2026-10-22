package com.example.ejerciciopoo.data

class UsuarioNormal(var nivel:Int):Usuario() {
    override fun obtenerDescripcion()="${super.obtenerDescripcion()}-Nivel:$nivel"
}
