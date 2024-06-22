package com.miempresa.app_proyectoanimabd.Utiles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.miempresa.app_proyectoanimabd.Entidades.Consultor
import com.miempresa.app_proyectoanimabd.Entidades.Empleado
import com.miempresa.app_proyectoanimabd.R

class ConsultorAdapter(context: Context,
    private val lista: List<Consultor>):ArrayAdapter<Consultor>(context, R.layout.list_item_consultor, lista)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.list_item_consultor, parent, false)

        val consultor = getItem(position)

        val textCodigo = itemView.findViewById<TextView>(R.id.textCodigo)
        textCodigo.text = "Código: ${consultor?.codconsul}"

        val textNombres = itemView.findViewById<TextView>(R.id.textNombres)
        textNombres.text = "Nombres: ${consultor?.nomconsul} ${consultor?.apeconsul}"

        val textDni = itemView.findViewById<TextView>(R.id.textDni)
        textDni.text = "Dni: ${consultor?.dni}"

        val textCorreo = itemView.findViewById<TextView>(R.id.textCorreo)
        textCorreo.text = "Correo: ${consultor?.correo}"

        val textEspecialidad = itemView.findViewById<TextView>(R.id.textEspecialidad)
        textEspecialidad.text = "Especialidad: ${consultor?.codesp}"

        return itemView
    }
}