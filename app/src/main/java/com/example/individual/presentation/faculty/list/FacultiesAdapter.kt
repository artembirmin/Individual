package com.example.individual.presentation.faculty.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Faculty

class FacultiesAdapter(
    private val onFacultyClick: (Faculty) -> Unit,
    private val onFacultyLongClick: (Faculty) -> Unit
) : RecyclerView.Adapter<FacultiesAdapter.ViewHolder>() {

    var items: List<Faculty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_faculty, parent, false)
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

        fun bind(faculty: Faculty) {
            tvName.text = faculty.name
            itemView.setOnClickListener {
                onFacultyClick(faculty)
            }
            itemView.setOnLongClickListener {
                onFacultyLongClick(faculty)
                true
            }
        }
    }
}