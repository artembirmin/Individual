/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.department.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.DepartmentShort
import com.example.individual.utils.DialogUtils
import com.example.individual.utils.toReadableDateTime

class DepartmentsAdapter(
    private val onFullInfoClick: (DepartmentShort) -> Unit,
    private val onBoardNumberClick: (DepartmentShort) -> Unit,
    private val onFlightNumberClick: (DepartmentShort) -> Unit,
) : RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    var items: List<DepartmentShort> = emptyList()
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
        private val tvOnboardNumber = itemView.findViewById<TextView>(R.id.tvOnboardNumber)
        private val tvFlightNumber = itemView.findViewById<TextView>(R.id.tvFlightNumber)
        private val tvOnboardNumberTitle =
            itemView.findViewById<TextView>(R.id.tvOnboardNumberTitle)
        private val tvFlightNumberTitle = itemView.findViewById<TextView>(R.id.tvFlightNumberTitle)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)

        fun bind(department: DepartmentShort) {
            tvOnboardNumber.text = department.onboardNumber
            tvFlightNumber.text = department.flightNumber

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByAlertDialog(
                    itemView.context,
                    title = "Дополнительная информация",
                    message = "Время вылета: ${department.boardingDateTime.toReadableDateTime()}" +
                            "\nОткуда: ${department.flightFrom}" +
                            "\nКуда: ${department.flightTo}"
                )
            }

            tvOnboardNumberTitle.setOnLongClickListener {
                onBoardNumberClick(department)
                true
            }
            tvFlightNumberTitle.setOnLongClickListener {
                onFlightNumberClick(department)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(department) }
        }
    }
}