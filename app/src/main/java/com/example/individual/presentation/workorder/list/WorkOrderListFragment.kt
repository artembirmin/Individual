package com.example.individual.presentation.workorder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentOrderListBinding
import com.example.individual.model.WorkOrderShort
import com.example.individual.presentation.BaseFragment

class WorkOrderListFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderListBinding
    private lateinit var viewModel: WorkOrderListViewModel

    private val adapter by lazy {
        WorkOrdersAdapter(
            onFullInfoClick = { workOrderShort ->
                navigator?.navigateToWorkOrderCreateEdit(
                    workOrderShort.serviceStationId,
                    workOrderShort.id
                )
            },
            onNumberClick = {
                sortByNumber()
            },
            onWorkDateClick = {
                sortByWorkDate()
            })
    }

    private fun sortByNumber() {
        adapter.items = adapter.items.sortedBy { it.number }
    }

    private fun sortByWorkDate() {
        adapter.items = adapter.items.sortedByDescending { it.workDate }
    }

    private val initParams: WorkOrderListFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_order_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvWorkOrders.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { super.closeFragment() }
        binding.tvTitle.text = initParams.serviceStationName
        binding.btnAdd.setOnClickListener {
            navigator?.navigateToWorkOrderCreateEdit(initParams.serviceStationId)
        }

        viewModel = ViewModelProvider(this).get(WorkOrderListViewModel::class.java)
        viewModel.getWorkOrders(initParams.serviceStationId)
        viewModel.workOrdersLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(workOrderFulls: List<WorkOrderShort>) {
        adapter.items = workOrderFulls.sortedByDescending { it.number }
    }

    companion object {
        fun newInstance(workOrderListFragmentInitParams: WorkOrderListFragmentInitParams): WorkOrderListFragment =
            WorkOrderListFragment().provideInitParams(workOrderListFragmentInitParams) as WorkOrderListFragment
    }
}
