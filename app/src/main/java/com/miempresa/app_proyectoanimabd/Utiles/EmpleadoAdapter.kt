package com.miempresa.app_proyectoanimabd.Utiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.R

class EmpleadoAdapter(context: Context,
    private val lista: List<Empleado>):ArrayAdapter<Empleado>(context, R.layout.list_item_empleado, lista)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.list_item_empleado, parent, false)

        val empleado = getItem(position)

        val textCodigo = itemView.findViewById<TextView>(R.id.textCodigo)
        textCodigo.text = "Código: ${empleado?.codemp}"

        val textNombres = itemView.findViewById<TextView>(R.id.textNombres)
        textNombres.text = "Nombres: ${empleado?.nomemp} ${empleado?.apeemp}"

        val textDni = itemView.findViewById<TextView>(R.id.textDni)
        textDni.text = "Dni: ${empleado?.dni}"

        val textFechaNacimiento = itemView.findViewById<TextView>(R.id.textFechaNacimiento)
        textFechaNacimiento.text = "Fecha de nacimiento: ${empleado?.fecnac}"

        return itemView
    }
}