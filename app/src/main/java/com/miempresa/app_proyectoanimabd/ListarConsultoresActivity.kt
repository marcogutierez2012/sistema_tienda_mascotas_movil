package com.miempresa.app_proyectoanimabd

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.miempresa.app_proyectoanimabd.Api.ANIMABDAPI
import com.miempresa.app_proyectoanimabd.Api.RetrofitHelper
import com.miempresa.app_proyectoanimabd.Entidades.Consultor
import com.miempresa.app_proyectoanimabd.Utiles.ConsultorAdapter
import com.miempresa.app_proyectoanimabd.databinding.ActivityListarConsultoresBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ListarConsultoresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListarConsultoresBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofitConsultor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarConsultoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnListarConsultores.setOnClickListener {
            listarConsultores()
        }

        binding.btnConsultarConsultor.setOnClickListener {
            listarConsultoresFiltro()
        }

        binding.btnNuevoConsultor.setOnClickListener {
            var i = Intent(this,
                NuevoConsultorActivity::class.java)
            //
            startActivity(i)
        }

        binding.lvConsultores.setOnItemLongClickListener { parent, view, position, id ->
            val consultorseleccionado = parent.getItemAtPosition(position) as Consultor
            mostrarDetallesConsultor(consultorseleccionado)
            true
        }

        binding.btnConsultorMenu.setOnClickListener {
            var i = Intent(this,
                MenuPrincipalActivity::class.java)
            //
            startActivity(i)
        }
    }

    private fun mostrarDetallesConsultor(consultor: Consultor) {
        val dialogo = AlertDialog.Builder(this)
        dialogo.setTitle("Consultor")
        dialogo.setMessage("Código: ${consultor.codconsul}"+"\n"+
                           "Nombres: ${consultor.nomconsul} ${consultor.apeconsul}")
        dialogo.setPositiveButton("Editar",
            DialogInterface.OnClickListener { dialog, which ->
                redireccionarActualizarConsultor(consultor)
            } )
        dialogo.setNeutralButton("Eliminar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarConsultor(consultor)
            })
        dialogo.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        dialogo.show()
    }

    private fun eliminarConsultor(consultor: Consultor){
        CoroutineScope(Dispatchers.IO).launch {
            DeleteConsultor(consultor)
            listarConsultores()
        }
    }

    private suspend fun DeleteConsultor(consultor: Consultor){

        var retorno: Response<Consultor>?=null
        try {
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.DeleteConsultor(obj = consultor)
        }
        catch (e: java.lang.Exception)
        {
            e.printStackTrace()
        }
        runOnUiThread(Runnable {
            mostrarDialogoConsultorEliminado()
        })
    }

    private fun mostrarDialogoConsultorEliminado() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Eliminación Exitosa")
        alertDialog.setMessage("¡Consultor Eliminado Correctamente!")
        alertDialog.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    private fun redireccionarActualizarConsultor(consultor: Consultor){
        var i = Intent(this,
            ActualizarConsultorActivity::class.java)
        i.putExtra("codconsul", consultor.codconsul)
        i.putExtra("nomconsul", consultor.nomconsul)
        i.putExtra("apeconsul", consultor.apeconsul)
        i.putExtra("dni", consultor.dni)
        i.putExtra("correo", consultor.correo)
        i.putExtra("codesp", consultor.codesp)
        //
        startActivity(i)
    }

    private fun listarConsultores() {
        CoroutineScope(Dispatchers.IO).launch {
            GetConsultores()
        }
    }

    private fun listarConsultoresFiltro() {
        CoroutineScope(Dispatchers.IO).launch {
            GetConsultoresFiltro()
        }
    }

    private suspend fun GetConsultores(){
        var retorno: Response<List<Consultor>>? = null
        try{
            val apicall = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall.GetConsultor()
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
                    mostrarConsultorAdapter(lista!!)
                    Toast.makeText(applicationContext,
                        "Cantidad de Consultores: $contador", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private suspend fun GetConsultoresFiltro(){

        var consulta = binding.tiedtDatoConsultor.text.toString()

        var retorno: Response<List<Consultor>>? = null
        try{
            val apicall1 = retrofit.create(ANIMABDAPI::class.java)
            retorno = apicall1.GetConsultorFiltro(consulta)
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
                    mostrarConsultorAdapter(lista!!)
                    Toast.makeText(applicationContext,
                        "Empleados Encontrados: $contador", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    private fun mostrarConsultorAdapter(lista: List<Consultor>) {
        val adaptador = ConsultorAdapter(this, lista)
        binding.lvConsultores.adapter = adaptador
    }

}