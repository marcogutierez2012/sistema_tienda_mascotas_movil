package com.miempresa.app_proyectoanimabd

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.Utiles.EmpleadoAdapter
import com.miempresa.app_proyectoanimabd.databinding.ActivityListarEmpleadosBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ListarEmpleadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarEmpleadosBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitEmpleado
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarEmpleadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnListarEmpleados.setOnClickListener {
            listarEmpleados()
        }

        binding.btnConsultarEmpleado.setOnClickListener {
            listarEmpleadosFiltro()
        }

        binding.btnNuevoEmpleado.setOnClickListener {
            var i = Intent(this,
                NuevoEmpleadoActivity::class.java)
            //
            startActivity(i)
        }

        binding.lvEmpleados.setOnItemLongClickListener { parent, view, position, id ->
            val empleadoseleccionado = parent.getItemAtPosition(position) as Empleado
            mostrarDetallesEmpleado(empleadoseleccionado)
            true
        }

        binding.btnMenuEmpleado.setOnClickListener {
            var i = Intent(this,
                MenuPrincipalActivity::class.java)
            //
            startActivity(i)
        }
    }

    private fun mostrarDetallesEmpleado(empleado: Empleado) {
        val dialogo = AlertDialog.Builder(this)
        dialogo.setTitle("Empleado")
        dialogo.setMessage("Código: ${empleado.codemp}"+"\n"+
                           "Nombres: ${empleado.nomemp} ${empleado.apeemp}")
        dialogo.setPositiveButton("Editar",
            DialogInterface.OnClickListener { dialog, which ->
                redireccionarActualizarEmpleado(empleado)
            } )
        dialogo.setNeutralButton("Eliminar",
            DialogInterface.OnClickListener { dialog, which ->
                deleteEmpleado(empleado)
            })
        dialogo.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        dialogo.show()
    }

    private fun deleteEmpleado(empleado:Empleado){
        CoroutineScope(Dispatchers.IO).launch {
            DeleteEmpleado(empleado)
            listarEmpleados()
        }
    }

    private suspend fun DeleteEmpleado(empleado: Empleado){

        var retorno: Response<Empleado>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.DeleteEmpleado(obj = empleado)
        }
        catch (e: java.lang.Exception)
        {
            e.printStackTrace()
        }
            runOnUiThread(Runnable {
                mostrarDialogoEmpleadoEliminado()
            })
    }

    private fun mostrarDialogoEmpleadoEliminado() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Eliminación Exitosa")
        alertDialog.setMessage("¡Empleado Eliminado Correctamente!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun redireccionarActualizarEmpleado(empleado: Empleado){
        var i = Intent(this,
            ActualizarEmpleadoActivity::class.java)
        i.putExtra("codemp", empleado.codemp)
        i.putExtra("nomemp", empleado.nomemp)
        i.putExtra("apeemp", empleado.apeemp)
        i.putExtra("dni", empleado.dni)
        i.putExtra("fecnac", empleado.fecnac)
        //
        startActivity(i)
    }

    private fun listarEmpleados() {
        CoroutineScope(Dispatchers.IO).launch {
            GetEmpleados()
        }
    }

    private fun listarEmpleadosFiltro() {
        CoroutineScope(Dispatchers.IO).launch {
            GetEmpleadosFiltro()
        }
    }

    private suspend fun GetEmpleados(){
        var retorno: Response<List<Empleado>>? = null
        try{
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.GetEmpleado()
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }

        if(retorno != null){
            if(retorno.isSuccessful)
            {
                val lista = retorno.body()
                var contador = lista?.size
                //
                runOnUiThread(Runnable {
                    mostrarEmpleadosAdapter(lista!!)
                    Toast.makeText(applicationContext,
                        "Cantidad de Empleados: $contador", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private suspend fun GetEmpleadosFiltro(){

        var consulta = binding.tiedtDatoEmpleado.text.toString()

        var retorno: Response<List<Empleado>>? = null
        try{
            val apicall1 = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall1.GetEmpleadoFiltro(consulta)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }
        if(retorno != null){
            if(retorno.isSuccessful)
            {
                val lista = retorno.body()
                var contador = lista?.size
                //
                runOnUiThread(Runnable {
                    mostrarEmpleadosAdapter(lista!!)
                    Toast.makeText(applicationContext,
                        "Empleados Encontrados: $contador", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun mostrarEmpleadosAdapter(lista: List<Empleado>) {
        val adaptador = EmpleadoAdapter(this, lista)
        binding.lvEmpleados.adapter = adaptador
    }
}