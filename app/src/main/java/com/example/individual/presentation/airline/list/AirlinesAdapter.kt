/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.airline.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Airline

class AirlinesAdapter(private val onAirlineClick: (Airline) -> Unit) :
    RecyclerView.Adapter<AirlinesAdapter.ViewHolder>() {

    var items: List<Airline> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_airline, parent, false)
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

        fun bind(airline: Airline) {
            tvName.text = airline.name
            itemView.setOnClickListener {
                onAirlineClick(airline)
            }
        }
    }
}