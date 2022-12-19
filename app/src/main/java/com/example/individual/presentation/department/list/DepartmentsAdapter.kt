package com.example.individual.presentation.department.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Department
import com.example.individual.utils.DialogUtils

class DepartmentsAdapter(
    private val onFullInfoClick: (Department) -> Unit,
    private val onNameClick: (Department) -> Unit,
    private val onEmployeesClick: (Department) -> Unit,
) : RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    var items: List<Department> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_department, parent, false)
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
        private val tvNameTitle =
            itemView.findViewById<TextView>(R.id.tvNameTitle)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)
        private val tvEmployees = itemView.findViewById<TextView>(R.id.tvEmployees)

        fun bind(department: Department) {
            tvName.text = department.name

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByAlertDialog(
                    itemView.context,
                    title = "Дополнительная информация",
                    message = "Количество сотрудников: ${department.name}"
                )
            }

            tvNameTitle.setOnLongClickListener {
                onNameClick(department)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(department) }
            tvEmployees.setOnClickListener { onEmployeesClick(department) }
        }
    }
}