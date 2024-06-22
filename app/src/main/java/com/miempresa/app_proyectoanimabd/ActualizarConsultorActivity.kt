package com.miempresa.app_proyectoanimabd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Consultor
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.databinding.ActivityActualizarConsultorBinding
import com.miempresa.app_proyectoanimabd.databinding.ActivityActualizarEmpleadoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ActualizarConsultorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActualizarConsultorBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitConsultor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarConsultorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codconsul = intent.getStringExtra("codconsul")
        val nomconsul = intent.getStringExtra("nomconsul")
        val apeconsul = intent.getStringExtra("apeconsul")
        val dni = intent.getStringExtra("dni")
        val correo = intent.getStringExtra("correo")
        val codesp = intent.getStringExtra("codesp")?.toInt()

        binding.edtcodconsul.setText(codconsul)
        binding.edtnomconsul.setText(nomconsul)
        binding.edtapeconsul.setText(apeconsul)
        binding.edtdniconsul.setText(dni)
        binding.edtcorreoconsul.setText(correo)
        binding.edtcodesp.setText(codesp!!)

        binding.btnActualizarConsultor.setOnClickListener {
            actualizarConsultor()
        }

        binding.btnRegresarListadoConsultor.setOnClickListener {
            var i = Intent(this,
                ListarConsultoresActivity::class.java)
            //
            startActivity(i)
        }
    }

    private fun actualizarConsultor(){
        CoroutineScope(Dispatchers.IO).launch {
            PutConsultor()
        }
    }

    private suspend fun PutConsultor(){

        var xcodcon = binding.edtcodconsul.text.toString()
        var xnomcon = binding.edtnomconsul.text.toString()
        var xapecon = binding.edtapeconsul.text.toString()
        var xdnicon = binding.edtdniconsul.text.toString()
        var xcorreo = binding.edtcorreoconsul.text.toString()
        var xcodesp = binding.edtcodesp.text.toString().toInt()

        var consultor = Consultor(xcodcon,xnomcon, xapecon, xdnicon, xcorreo,xcodesp,"No")

        var retorno: Response<Consultor>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.PutConsultor(obj = consultor)
        }
        catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        runOnUiThread(Runnable {
            mostrarDialogoConsultorActualizado()
        })
    }

    private fun mostrarDialogoConsultorActualizado() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Actualización Exitosa")
        alertDialog.setMessage("¡Consultor Actualizado Correctamente!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}