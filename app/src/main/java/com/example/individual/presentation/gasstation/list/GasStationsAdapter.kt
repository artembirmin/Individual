/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.gasstation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.GasStation

class GasStationsAdapter(
    private val onGasStationClick: (GasStation) -> Unit,
    private val onFullInfoClick: (GasStation) -> Unit
) : RecyclerView.Adapter<GasStationsAdapter.ViewHolder>() {

    var items: List<GasStation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_gas_station, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bind(gasStation: GasStation) {
            tvName.text = gasStation.name
            itemView.setOnClickListener {
                onGasStationClick(gasStation)
            }
            itemView.setOnLongClickListener {
                onFullInfoClick(gasStation)
                true
            }
        }
    }
}