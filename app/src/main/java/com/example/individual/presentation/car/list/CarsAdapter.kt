/*
 * Attendance
 *
 * Created by artembirmin on 30/11/2022.
 */

package com.example.individual.presentation.car.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.CarShort
import com.example.individual.utils.DialogUtils

class CarsAdapter(
    private val onFullInfoClick: (CarShort) -> Unit,
    private val onBoardNumberClick: (CarShort) -> Unit,
    private val onFlightNumberClick: (CarShort) -> Unit,
) : RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    var items: List<CarShort> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_car, parent, false)
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

        fun bind(car: CarShort) {
            tvNumber.text = car.number
            tvFuelType.text = car.fuelType.toString()

            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByToast(
                    itemView.context,
                    message = "Объем залитого топлива: ${car.fuelVolume} литров"
                )
            }

            tvNumberTitle.setOnLongClickListener {
                onBoardNumberClick(car)
                true
            }
            tvFuelTypeTitle.setOnLongClickListener {
                onFlightNumberClick(car)
                true
            }
            tvFullInfo.setOnClickListener { onFullInfoClick(car) }
        }
    }
}