/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.order.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.OrderShort
import com.example.individual.utils.DialogUtils
import com.example.individual.utils.toReadableDateTime

class OrdersAdapter(
    private val onFullInfoClick: (OrderShort) -> Unit,
    private val onAmountClick: (OrderShort) -> Unit,
    private val onDateClick: (OrderShort) -> Unit,
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    var items: List<OrderShort> = emptyList()
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
        private val tvAmount = itemView.findViewById<TextView>(R.id.tvAmount)
        private val tvAmountTitle = itemView.findViewById<TextView>(R.id.tvAmountTitle)
        private val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        private val tvDateTitle = itemView.findViewById<TextView>(R.id.tvDateTitle)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)

        fun bind(order: OrderShort) {
            val amount = "${order.amount} ${order.currency}"
            tvAmount.text = amount

            tvDate.text = order.orderDateTime.toReadableDateTime()

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByToast(
                    itemView.context,
                    message = "Неоплаченная стоимость: ${order.unpaidAmount} ${order.currency}"
                )
            }

            tvAmountTitle.setOnLongClickListener {
                onAmountClick(order)
                true
            }
            tvDateTitle.setOnLongClickListener {
                onDateClick(order)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(order) }
        }
    }
}