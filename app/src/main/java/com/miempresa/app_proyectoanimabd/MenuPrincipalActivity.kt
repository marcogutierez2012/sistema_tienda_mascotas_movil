package com.miempresa.app_proyectoanimabd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miempresa.app_proyectoanimabd.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgbtnEmpleado.setOnClickListener {
            var i = Intent(this,
                ListarEmpleadosActivity::class.java)
            //
            startActivity(i)
        }

        binding.imgbtnCliente.setOnClickListener {

        }

        binding.imgbtnConsultor.setOnClickListener {
            var i = Intent(this,
                ListarConsultoresActivity::class.java)
            //
            startActivity(i)
        }

        binding.imgbtnVenta.setOnClickListener {

        }

        binding.imgbtnProducto.setOnClickListener {

        }

        binding.imgbtnDetalle.setOnClickListener {

        }
    }
}