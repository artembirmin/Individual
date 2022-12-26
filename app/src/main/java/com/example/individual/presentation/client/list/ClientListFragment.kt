package com.example.individual.presentation.client.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.databinding.FragmentClientsListBinding
import com.example.individual.model.Client
import com.example.individual.presentation.BaseFragment

class ClientListFragment : BaseFragment() {
    private lateinit var binding: FragmentClientsListBinding
    private lateinit var viewModel: ClientListViewModel

    private val adapter by lazy {
        ClientsAdapter(
            onClientClick = { client ->
                navigator?.navigateToOrders(client)
            },
            onFullInfoClick = { client ->
                navigator?.navigateToClientCreateEdit(client.id)
            },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_clients_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvClients.adapter = adapter
        binding.btnAdd.setOnClickListener { navigator?.navigateToClientCreateEdit() }

        viewModel = ViewModelProvider(this).get(ClientListViewModel::class.java)
        viewModel.getClients()
        viewModel.clientsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(clients: List<Client>) {
        adapter.items = clients.sortedBy { it.name }
    }

    companion object {
        fun newInstance() = ClientListFragment()
    }
}
