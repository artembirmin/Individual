package com.example.individual.presentation.order.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrderCreateEditBinding
import com.example.individual.model.OrderFull
import com.example.individual.presentation.BaseFragment
import org.joda.time.DateTime

class OrderCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderCreateEditBinding
    private lateinit var viewModel: OrderCreateEditViewModel
    private val initParams: OrderCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_order_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tpOrderTime.setIs24HourView(true)
            toolbar.setNavigationOnClickListener { closeFragment() }
            btnSave.setOnClickListener {
                onSaveClick()
            }
            btnDelete.setOnClickListener {
                viewModel.deleteOrder()
                closeFragment()
            }
        }

        viewModel = ViewModelProvider(this).get(OrderCreateEditViewModel::class.java)

        val orderId = initParams.id
        if (orderId != null) {
            viewModel.getOrder(orderId)
        }

        viewModel.orderLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun onSaveClick() {
        with(binding) {
            val deliveryTimeInWorkDays =
                etDeliveryTimeInWorkDays.text.toString().toLongOrNull() ?: run {
                    showMessageByToast("Введите пассажиров")
                    return
                }
            val weight = etWeight.text.toString().toLongOrNull() ?: run {
                showMessageByToast("Введите объекм топлива")
                return
            }
            val amount = etAmount.text.toString().toLongOrNull() ?: run {
                showMessageByToast("Введите стоимость")
                return
            }
            val unpaidAmount = etUnpaidAmount.text.toString().toLongOrNull() ?: run {
                showMessageByToast("Введите неоплаченную стоимость")
                return
            }

            val order = OrderFull(
                id = 0,
                clientId = initParams.clientId,
                amount = amount,
                currency = etCurrency.text.toString(),
                unpaidAmount = unpaidAmount,
                deliveryTo = etDeliveryTo.text.toString(),
                orderDateTime = DateTime(
                    dpOrderDate.year,
                    dpOrderDate.month + 1,
                    dpOrderDate.dayOfMonth,
                    tpOrderTime.hour,
                    tpOrderTime.minute
                ),
                deliveryTimeInWorkDays = deliveryTimeInWorkDays,
                deliveryFrom = etDeliveryFrom.text.toString(),
                weight = weight
            )
            val message = when {
                order.currency.isBlank() -> "Введите валюту"
                order.deliveryTo.isBlank() -> "Введите адрес доставки"
                order.deliveryFrom.isBlank() -> "Введите адрес отправки"
                order.orderDateTime.isAfterNow -> "Время отправки не может быть больше текущего"
                else -> null
            }
            if (message == null) {
                viewModel.saveOrder(order)
                closeFragment()
            } else {
                showMessageByToast(message)
            }
        }
    }

    private fun updateUI(order: OrderFull?) {
        if (order != null) {
            with(binding) {
                etAmount.setText(order.amount.toString())
                etCurrency.setText(order.currency)
                etUnpaidAmount.setText(order.unpaidAmount.toString())
                etDeliveryTo.setText(order.deliveryTo)
                dpOrderDate.updateDate(
                    order.orderDateTime.year,
                    order.orderDateTime.monthOfYear - 1,
                    order.orderDateTime.dayOfMonth
                )
                tpOrderTime.hour = order.orderDateTime.hourOfDay
                tpOrderTime.minute = order.orderDateTime.minuteOfHour
                etDeliveryTimeInWorkDays.setText(order.deliveryTimeInWorkDays.toString())
                etDeliveryFrom.setText(order.deliveryFrom)
                etWeight.setText(order.weight.toString())
            }
        } else {
            with(binding) {
                val now = DateTime.now()
                dpOrderDate.updateDate(
                    now.year,
                    now.monthOfYear - 1,
                    now.dayOfMonth
                )
                tpOrderTime.hour = now.hourOfDay
                tpOrderTime.minute = now.minuteOfHour
            }
        }
    }

    companion object {
        fun newInstance(initParams: OrderCreateEditFragmentInitParams) =
            OrderCreateEditFragment().provideInitParams(initParams) as OrderCreateEditFragment
    }
}
