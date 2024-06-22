package com.miempresa.app_proyectoanimabd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Consultor
import com.miempresa.app_proyectoanimabd.databinding.ActivityNuevoConsultorBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class NuevoConsultorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNuevoConsultorBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitEmpleado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoConsultorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrarConsultor.setOnClickListener {
            registrarConsultor()
        }

        binding.btnLimpiarConsultor.setOnClickListener {
            limpiarEntradas()
        }

        binding.btnRegresarListaConsultor.setOnClickListener {
            var i = Intent(this,
                ListarConsultoresActivity::class.java)
            //
            startActivity(i)
        }
    }

    private fun registrarConsultor() {
        CoroutineScope(Dispatchers.IO).launch {
            PostConsultor()
        }
    }

    private fun limpiarEntradas(){
        binding.tiedtnomconsul.text?.clear()
        binding.tiedtapeconsul.text?.clear()
        binding.tiedtdniconsul.text?.clear()
        binding.tiedtcorreoconsul.text?.clear()
        binding.tiedtcodesp.text?.clear()
        binding.tiedtnomconsul.requestFocus()
    }

    private suspend fun PostConsultor(){

        var xnomcon = binding.tiedtnomconsul.text.toString()
        var xapecon = binding.tiedtapeconsul.text.toString()
        var xdnicon = binding.tiedtdniconsul.text.toString()
        var xcorreocon = binding.tiedtcorreoconsul.text.toString()
        var xcodesp = binding.tiedtcodesp.text.toString().toInt()

        var obj = Consultor("",xnomcon, xapecon, xdnicon, xcorreocon, xcodesp,"No")

        var retorno: Response<Consultor>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.PostConsultor(obj)
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
        alertDialog.setMessage("¡Nuevo Consultor Registrado!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}