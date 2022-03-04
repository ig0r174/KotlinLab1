package com.homework.lab1.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.homework.lab1.R


class TariffAdapter : ListAdapter<Tariff, TariffAdapter.ViewHolder>(ItemDiffCallback()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tariffName: TextView = view.findViewById(R.id.tariff_name)
        private val tariffPrice: TextView = view.findViewById(R.id.tariff_price)
        private val tariffDescription: TextView = view.findViewById(R.id.tariff_description)
        private val divider: View = view.findViewById(R.id.divider)
        private val context: Context = view.context

        fun bind(tariff: Tariff, isLastItem: Boolean) {
            tariffName.text = context.getString(R.string.tariff_name, tariff.name)
            tariffPrice.text = context.getString(R.string.money_string_int, tariff.amount.toInt())
            tariffDescription.text = tariff.description

            if (isLastItem){
                divider.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tariff_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == (itemCount - 1))
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Tariff>() {
    override fun areItemsTheSame(oldItem: Tariff, newItem: Tariff): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Tariff, newItem: Tariff): Boolean = oldItem == newItem
}
