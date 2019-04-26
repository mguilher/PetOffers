package com.mdr.petoffers.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdr.petoffers.R
import com.mdr.petoffers.models.AlertModel
import kotlinx.android.synthetic.main.alert_item.view.*

class MainListAdapter(
    val context: Context,
    val alerts: List<AlertModel>,
    val clickLista: (AlertModel) -> Unit
) :
    RecyclerView.Adapter<MainListAdapter.AlertViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AlertViewHolder {

        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.alert_item, p0, false)

        return AlertViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return alerts.size
    }

    override fun onBindViewHolder(p0: AlertViewHolder, position: Int) {
        val nota = alerts[position]
        p0.bindView(nota, clickLista)
    }


    class AlertViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(
            alert: AlertModel,
            clickLista: (AlertModel) -> Unit
        ) = with(itemView) {
            tvTitulo.text = alert.text
            tvDescricao.text = alert.text

            setOnClickListener { clickLista(alert) }
        }

    }
}