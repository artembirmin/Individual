/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.servicestation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.ServiceStation

class ServiceStationsAdapter(
    private val onServiceStationClick: (ServiceStation) -> Unit,
    private val onFullInfoClick: (ServiceStation) -> Unit
) : RecyclerView.Adapter<ServiceStationsAdapter.ViewHolder>() {

    var items: List<ServiceStation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_station, parent, false)
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

        fun bind(serviceStation: ServiceStation) {
            tvName.text = serviceStation.name
            itemView.setOnClickListener {
                onServiceStationClick(serviceStation)
            }
            itemView.setOnLongClickListener {
                onFullInfoClick(serviceStation)
                true
            }
        }
    }
}