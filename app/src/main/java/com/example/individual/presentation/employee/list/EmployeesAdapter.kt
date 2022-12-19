package com.example.individual.presentation.employee.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Employee

class EmployeesAdapter(
    private val onEmployeeClick: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    var items: List<Employee> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_employee, parent, false)
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
        private val tvBio = itemView.findViewById<TextView>(R.id.tvBio)
        private val tvPost = itemView.findViewById<TextView>(R.id.tvPost)
        private val tvExperience = itemView.findViewById<TextView>(R.id.tvExperience)

        fun bind(employee: Employee) {
            val resources = itemView.resources
            tvName.text = employee.getShortFullName()
            tvBio.text = employee.bio
            tvPost.text = resources.getString(R.string.employee_post, employee.post)
            tvExperience.text =
                resources.getString(R.string.employee_experience, employee.experienceInYears)

            itemView.setOnClickListener { onEmployeeClick(employee) }
        }
    }
}