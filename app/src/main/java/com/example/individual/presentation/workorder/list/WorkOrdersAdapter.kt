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
import com.example.individual.utils.toReadableDate

class WorkOrdersAdapter(
    private val onFullInfoClick: (WorkOrderShort) -> Unit,
    private val onNumberClick: (WorkOrderShort) -> Unit,
    private val onWorkDateClick: (WorkOrderShort) -> Unit,
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
        private val tvWorkDate = itemView.findViewById<TextView>(R.id.tvWorkDate)
        private val tvNumberTitle = itemView.findViewById<TextView>(R.id.tvNumberTitle)
        private val tvWorkDateTitle = itemView.findViewById<TextView>(R.id.tvWorkDateTitle)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)

        fun bind(workOrder: WorkOrderShort) {
            tvNumber.text = workOrder.number
            tvWorkDate.text = workOrder.workDate.toReadableDate()

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByToast(
                    itemView.context,
                    message = "Исполнитель работ: ${workOrder.workerName}"
                )
            }

            tvNumberTitle.setOnLongClickListener {
                onNumberClick(workOrder)
                true
            }
            tvWorkDateTitle.setOnLongClickListener {
                onWorkDateClick(workOrder)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(workOrder) }
        }
    }
}