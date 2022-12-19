package com.example.individual.presentation.group.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.individual.R
import com.example.individual.model.Group
import com.example.individual.utils.DialogUtils

class GroupsAdapter(
    private val onFullInfoClick: (Group) -> Unit,
    private val onStudentsClick: (Group) -> Unit,
    private val onNameClick: () -> Unit,
    private val onSpecialCodeClick: () -> Unit,
    private val onSpecialClick: () -> Unit,
    private val onProfileClick: () -> Unit,
) : RecyclerView.Adapter<GroupsAdapter.ViewHolder>() {

    var items: List<Group> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_group, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNameTitle = itemView.findViewById<TextView>(R.id.tvNameTitle)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val tvSpecialCodeTitle = itemView.findViewById<TextView>(R.id.tvSpecialCodeTitle)
        private val tvSpecialCode = itemView.findViewById<TextView>(R.id.tvSpecialCode)
        private val tvSpecialTitle = itemView.findViewById<TextView>(R.id.tvSpecialTitle)
        private val tvSpecial = itemView.findViewById<TextView>(R.id.tvSpecial)
        private val tvProfileTitle = itemView.findViewById<TextView>(R.id.tvProfileTitle)
        private val tvProfile = itemView.findViewById<TextView>(R.id.tvProfile)
        private val tvAdditionalInfo = itemView.findViewById<TextView>(R.id.tvAdditionalInfo)
        private val tvFullInfo = itemView.findViewById<TextView>(R.id.tvFullInfo)
        private val tvStudents = itemView.findViewById<TextView>(R.id.tvStudents)

        fun bind(group: Group) {
            tvName.text = group.numberGroup
            tvSpecialCode.text = group.specialCode
            tvSpecial.text = group.special
            tvProfile.text = group.profile

            tvNameTitle.setOnLongClickListener {
                onNameClick()
                true
            }
            tvSpecialCodeTitle.setOnLongClickListener {
                onSpecialCodeClick
                true
            }
            tvSpecialTitle.setOnLongClickListener {
                onSpecialClick
                true
            }
            tvProfileTitle.setOnLongClickListener {
                onProfileClick
                true
            }

            tvStudents.setOnClickListener { onStudentsClick(group) }
            tvFullInfo.setOnClickListener { onFullInfoClick(group) }
            tvAdditionalInfo.setOnClickListener {
                DialogUtils.showMessageByAlertDialog(
                    itemView.context,
                    title = "Дополнительная информация",
                    message = "Количество бюджетников: ${group.budgetStudentsCount}" +
                            "\nКоличество договорников: ${group.commerceStudentsCount}"
                )
            }
        }
    }
}