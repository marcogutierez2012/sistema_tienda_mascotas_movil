package com.miempresa.app_proyectoanimabd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.databinding.ActivityActualizarEmpleadoBinding
import com.miempresa.app_proyectoanimabd.databinding.ActivityListarEmpleadosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ActualizarEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActualizarEmpleadoBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitEmpleado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codemp = intent.getStringExtra("codemp")
        val nomemp = intent.getStringExtra("nomemp")
        val apeemp = intent.getStringExtra("apeemp")
        val dniemp = intent.getStringExtra("dni")
        val fecnac = intent.getStringExtra("fecnac")

        binding.edtcodemp.setText(codemp)
        binding.edtnomemp.setText(nomemp)
        binding.edtapeemp.setText(apeemp)
        binding.edtdniemp.setText(dniemp)
        binding.edtfecnac.setText(fecnac)

        binding.btnActualizarEmpleado.setOnClickListener {
            actualizarEmpleado()
        }

        binding.btnRegresarListadoEmpleado.setOnClickListener {
            var i = Intent(this,
                ListarEmpleadosActivity::class.java)
            //
            startActivity(i)
        }

    }

    private fun actualizarEmpleado(){
        CoroutineScope(Dispatchers.IO).launch {
            PutEmpleado()
        }
    }

    private suspend fun PutEmpleado(){

        var xcodemp = binding.edtcodemp.text.toString()
        var xnomemp = binding.edtnomemp.text.toString()
        var xapeemp = binding.edtapeemp.text.toString()
        var xdniemp = binding.edtdniemp.text.toString()
        var xfecnac = binding.edtfecnac.text.toString()

        var empleado = Empleado(xcodemp,xnomemp, xapeemp, xdniemp, xfecnac,"No")

        var retorno: Response<Empleado>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.PutEmpleado(obj = empleado)
        }
        catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        runOnUiThread(Runnable {
            mostrarDialogoEmpleadoActualizado()
        })
    }

    private fun mostrarDialogoEmpleadoActualizado() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Actualización Exitosa")
        alertDialog.setMessage("¡Empleado Actualizado Correctamente!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }
}