package com.example.individual.presentation.order.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrdersListBinding
import com.example.individual.model.OrderShort
import com.example.individual.presentation.BaseFragment

class OrderListFragment : BaseFragment() {
    private lateinit var binding: FragmentOrdersListBinding
    private lateinit var viewModel: OrderListViewModel

    private val adapter by lazy {
        OrdersAdapter(
            onFullInfoClick = { orderShort ->
                navigator?.navigateToOrderCreateEdit(
                    orderShort.clientId,
                    orderShort.id
                )
            },
            onAmountClick = {
                sortByAmount()
            },
            onDateClick = {
                sortByDate()
            })
    }

    private fun sortByAmount() {
        adapter.items = adapter.items.sortedByDescending { it.amount }
    }

    private fun sortByDate() {
        adapter.items = adapter.items.sortedByDescending { it.orderDateTime }
    }

    private val initParams: OrderListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_orders_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvOrders.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.clientName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToOrderCreateEdit(initParams.clientId)
        }

        viewModel = ViewModelProvider(this).get(OrderListViewModel::class.java)
        viewModel.getOrders(initParams.clientId)
        viewModel.ordersLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(orderFulls: List<OrderShort>) {
        adapter.items = orderFulls.sortedByDescending { it.amount }
    }

    companion object {
        fun newInstance(orderListFragmentInitParams: OrderListFragmentInitParams): OrderListFragment =
            OrderListFragment().provideInitParams(orderListFragmentInitParams) as OrderListFragment
    }
}
