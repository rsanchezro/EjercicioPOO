package com.example.ejerciciopoo.data

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.Date

open class Usuario (log:String, var password:String, f_nac:Date= Calendar.getInstance().apply( {  add(Calendar.YEAR,-18)}).time, var email:String=" ") {
    var login=log
        set(value) {
            if(value=="")
               field="desconocido"
            else
                field=value
        }
    var fechaNacimiento:Date?=f_nac
        set(value){
            //Se impide un usuario menor de edad, en caso de ser menor de edad
            //Fecha de nacimiento es null
            var fecha_18antes= Calendar.getInstance().apply { add(Calendar.YEAR,-18) }.time

            field=value?.takeIf( { !it.after(fecha_18antes)  })


        }

    /*fun obtenerDescripcion():String{
       return  "Login:$login-FechaNac:${fechaNacimiento!!.day}/${fechaNacimiento!!.month}/${fechaNacimiento!!.year}" +
               "-Email:$email"
    }*/
    open fun obtenerDescripcion()="Login:$login-FechaNac:${fechaNacimiento!!.day}/${fechaNacimiento!!.month}/${fechaNacimiento!!.year}" +
            "-Email:$email"

    constructor():this("","")
}