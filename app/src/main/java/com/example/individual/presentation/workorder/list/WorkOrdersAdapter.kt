/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.workorder.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.WorkOrderShort
import com.example.individual.utils.DialogUtils

class WorkOrdersAdapter(
    private val onFullInfoClick: (WorkOrderShort) -> Unit,
    private val onNumberClick: (WorkOrderShort) -> Unit,
    private val onFuelTypeClick: (WorkOrderShort) -> Unit,
) : RecyclerView.Adapter<WorkOrdersAdapter.ViewHolder>() {

    var items: List<WorkOrderShort> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_order, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNumber = itemView.findViewById<TextView>(R.id.tvNumber)
        private val tvFuelType = itemView.findViewById<TextView>(R.id.tvFuelType)
        private val tvNumberTitle = itemView.findViewById<TextView>(R.id.tvNumberTitle)
        private val tvFuelTypeTitle = itemView.findViewById<TextView>(R.id.tvFuelTypeTitle)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)

        fun bind(workOrder: WorkOrderShort) {
            tvNumber.text = workOrder.number
            tvFuelType.text = workOrder.workerName.toString()

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByToast(
                    itemView.context,
                    message = "Объем залитого топлива: ${workOrder.workDate} литров"
                )
            }

            tvNumberTitle.setOnLongClickListener {
                onNumberClick(workOrder)
                true
            }
            tvFuelTypeTitle.setOnLongClickListener {
                onFuelTypeClick(workOrder)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(workOrder) }
        }
    }
}