package com.example.ejerciciopoo

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejerciciopoo.data.Administrador
import com.example.ejerciciopoo.data.Usuario
import com.example.ejerciciopoo.data.UsuarioNormal
import com.example.ejerciciopoo.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Defino la coleccion Usuario para almacenar usuarios
    val lista_usuarios=mutableListOf<Usuario>()


    //Declaro variable para hacer el viewbinding
    lateinit var mibinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Instancio el objeto mibinding
        mibinding= ActivityMainBinding.inflate(layoutInflater)
        //Establezco la vista a mi activity
        setContentView(mibinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //************** SPINNER *************************/
        val adaptador_spinner= ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item,
            arrayOf("UsuarioNormal","Adminastrador"))
        adaptador_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mibinding.spinnerTipoUsuario.adapter=adaptador_spinner

        //Añado escuchador al spinner

        mibinding.spinnerTipoUsuario.onItemSelectedListener=
        object: AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p0?.selectedItem=="UsuarioNormal")
                {
                    mibinding.miframelayout.editTextNivel.visibility=View.VISIBLE
                    mibinding.miframelayout.editTextArea.visibility=View.GONE
                }
                else
                {
                    mibinding.miframelayout.editTextNivel.visibility=View.GONE
                    mibinding.miframelayout.editTextArea.visibility=View.VISIBLE
                }


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }



        //Defino el escuchado al hacer click en el boton añadir Usuario
        mibinding.buttonDarAlta.setOnClickListener{
            var usuario:Usuario?=null
            //Averiguar que tipo de Usuario tengo que crear
            if(mibinding.spinnerTipoUsuario.selectedItem.toString()=="UsuarioNormal")
            {
                usuario= UsuarioNormal(Random.nextInt(1,10))
            }
            else
            {
                usuario= Administrador("")
            }
            //Deberia rellenar los atributos de usuario
            //con los datos de los elementos del formulario
            usuario.apply {
                login=mibinding.editTextLogin.toString()
                password=mibinding.editTextPassword.toString()
                if(mibinding.editTextFechaNacimiento.toString()!="")
                    fechaNacimiento= SimpleDateFormat("dd-MM-yyyy",Locale.getDefault()).parse(mibinding.editTextFechaNacimiento.text.toString())
               email=mibinding.editTextEmail.toString()
            }


            //Compruebo si existe el usuario con un login
            // determinado en la lista



            //si he encontrado el elemento
            if(lista_usuarios.indexOfFirst {elemento_lista->  usuario.login==elemento_lista.login}!=-1){
                Toast.makeText(this,"Usuario ya existe",Toast.LENGTH_LONG).show()
            }
            else
            {//Usuario no existe, se añade a la lista
                //Vaciamos los campos del formulario
                limpiar_campos()

                lista_usuarios.add(usuario)
                //Mostramos un Toast
                Toast.makeText(this,"Usuario añadido correctamente",Toast.LENGTH_SHORT).show()
            }

            lista_usuarios.indexOf(usuario)
        }
        //Defino eschuchador al hacer click para el editText Fecha Nacimiento
        mibinding.editTextFechaNacimiento.setOnClickListener {
            //Creo un objeto DatePickerDialog,
            //que sirve para definir un Dialogo con un calendario
            var fecha_actual= Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this,
                { view, año, mes, dia ->
                    val diaElegido = "$dia-$mes-$año"
                //Necesito que se muestre la fecha en el editTextFechanacimiento
                    mibinding.editTextFechaNacimiento.setText(diaElegido)

                }, fecha_actual.get(Calendar.YEAR), fecha_actual.get(Calendar.MONTH), fecha_actual.get(Calendar.DAY_OF_MONTH))

            datePickerDialog.show()



        }



    }

    fun limpiar_campos() {
        mibinding.editTextLogin.setText("")
        mibinding.editTextPassword.setText("")
        mibinding.editTextFechaNacimiento.setText("")
        mibinding.editTextEmail.setText("")

    }
}

