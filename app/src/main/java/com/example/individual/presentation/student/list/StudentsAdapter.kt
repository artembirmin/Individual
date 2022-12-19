package com.example.individual.presentation.student.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Student

class StudentsAdapter(
    private val onStudentClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    var items: List<Student> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_student, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvFormOfEducation = itemView.findViewById<TextView>(R.id.tvFormOfEducation)

        fun bind(student: Student) {
            tvName.text = student.fio

            if (student.isOnBudget) {
                tvFormOfEducation.setText(R.string.student_budget_form)
            } else {
                tvFormOfEducation.setText(R.string.student_commerce_form)
            }

            itemView.setOnClickListener { onStudentClick(student) }
        }
    }
}