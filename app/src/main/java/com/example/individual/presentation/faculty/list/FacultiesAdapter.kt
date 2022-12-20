package com.example.individual.presentation.faculty.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Faculty

// Адаптер списка. Предоставляет вьюхе данные
// https://developer.alexanderklimov.ru/android/views/recyclerview-kot.php
// https://devcolibri.com/unit/%D1%83%D1%80%D0%BE%D0%BA-10-%D1%80%D0%B0%D0%B1%D0%BE%D1%82%D0%B0-%D1%81-recyclerview-%D0%BD%D0%B0-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80%D0%B5-tweetsrecyclerview-2/
class FacultiesAdapter(
    // Листенеры в виде лямбд
    private val onFacultyClick: (Faculty) -> Unit,
    private val onFacultyLongClick: (Faculty) -> Unit
) : RecyclerView.Adapter<FacultiesAdapter.ViewHolder>() {

    // Отображаемые элементы
    var items: List<Faculty> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged() // Обновляет список после изменения данных
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