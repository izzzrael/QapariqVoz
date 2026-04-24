package com.example.yapevoz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YapeAdapter(private val listaPagos: List<PagoYape>) :
    RecyclerView.Adapter<YapeAdapter.PagoViewHolder>() {


    class PagoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.itemNombre)
        val monto: TextView = view.findViewById(R.id.itemMonto)
        val hora: TextView = view.findViewById(R.id.itemHora)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pago, parent, false)
        return PagoViewHolder(view)
    }


    override fun onBindViewHolder(holder: PagoViewHolder, position: Int) {
        val pago = listaPagos[position]
        holder.nombre.text = pago.nombre
        holder.monto.text = "S/ ${pago.monto}"
        holder.hora.text = pago.hora
    }

    override fun getItemCount() = listaPagos.size
}