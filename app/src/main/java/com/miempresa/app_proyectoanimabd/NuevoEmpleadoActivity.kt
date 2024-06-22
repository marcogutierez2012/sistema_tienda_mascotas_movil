package com.miempresa.app_proyectoanimabd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.databinding.ActivityNuevoEmpleadoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NuevoEmpleadoActivity : AppCompatActivity() {


    private lateinit var binding: ActivityNuevoEmpleadoBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitEmpleado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarEmpleado.setOnClickListener {
            registrarEmpleado()
        }

        binding.btnLimpiarEmpleado.setOnClickListener {
            limpiarEntradas()
        }

        binding.btnRegresarListaEmpleado.setOnClickListener {
            var i = Intent(this,
                ListarEmpleadosActivity::class.java)
            //
            startActivity(i)
        }
    }

    private fun registrarEmpleado() {
        CoroutineScope(Dispatchers.IO).launch {
            PostEmpleado()
        }
    }

    private fun limpiarEntradas(){
        binding.tiedtnomemp.text?.clear()
        binding.tiedtapeemp.text?.clear()
        binding.tiedtdniemp.text?.clear()
        binding.tiedtfecnac.text?.clear()
        binding.tiedtnomemp.requestFocus()
    }

    private suspend fun PostEmpleado(){

        var xnomemp = binding.tiedtnomemp.text.toString()
        var xapeemp = binding.tiedtapeemp.text.toString()
        var xdniemp = binding.tiedtdniemp.text.toString()
        var xfecnac = binding.tiedtfecnac.text.toString()

        var obj = Empleado("",xnomemp, xapeemp, xdniemp, xfecnac,"No")

        var retorno: Response<Empleado>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.PostEmpleado(obj)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
            runOnUiThread(Runnable {
                mostrarDialogoRegistroExitoso()
            })
    }

    private fun mostrarDialogoRegistroExitoso() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Registro Exitoso")
        alertDialog.setMessage("¡Nuevo Empleado Registrado!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}