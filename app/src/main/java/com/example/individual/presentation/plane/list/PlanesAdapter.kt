/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.plane.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.PlaneShort
import com.example.individual.utils.DialogUtils
import com.example.individual.utils.toReadableDateTime

class PlanesAdapter(
    private val onEditClick: (PlaneShort) -> Unit,
    private val onDeleteClick: (PlaneShort) -> Unit,
    private val onBoardNumberClick: (PlaneShort) -> Unit,
    private val onFlightNumberClick: (PlaneShort) -> Unit,
) : RecyclerView.Adapter<PlanesAdapter.ViewHolder>() {

    var items: List<PlaneShort> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_plane, parent, false)
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

        fun bind(plane: PlaneShort) {
            tvOnboardNumber.text = plane.onboardNumber
            tvFlightNumber.text = plane.flightNumber

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByAlertDialog(
                    itemView.context,
                    title = "Дополнительная информация",
                    message = "Время вылета: ${plane.boardingDateTime.toReadableDateTime()}" +
                            "\nОткуда: ${plane.flightFrom}" +
                            "\nКуда: ${plane.flightTo}"
                )
            }

            tvOnboardNumberTitle.setOnLongClickListener {
                onBoardNumberClick(plane)
                true
            }
            tvFlightNumberTitle.setOnLongClickListener {
                onFlightNumberClick(plane)
                true
            }

            itemView.setOnClickListener {
                onEditClick(plane)
            }

            itemView.setOnLongClickListener {
                showDialog(itemView.context, plane)
                true
            }
        }

        private fun showDialog(context: Context, plane: PlaneShort) {
            DialogUtils.showMessageByAlertDialog(
                context,
                message = "Вы действительно хотите удалить самолет?",
                negativeText = "Отмена",
                positiveText = "Да",
                onPositiveButtonClick = { onDeleteClick(plane) }
            )
        }
    }
}