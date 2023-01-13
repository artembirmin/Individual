package com.example.individual.presentation.client.createedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.individual.R
import com.example.individual.common.getInitParams
import com.example.individual.common.provideInitParams
import com.example.individual.databinding.FragmentClientCreateEditBinding
import com.example.individual.model.Client
import com.example.individual.presentation.BaseFragment

class ClientCreateEditFragment : BaseFragment() {
    private lateinit var binding: FragmentClientCreateEditBinding
    private lateinit var viewModel: ClientCreateEditViewModel
    private val initParams: ClientCreateEditFragmentInitParams by lazy { getInitParams() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_client_create_edit,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { closeFragment() }
        binding.btnSave.setOnClickListener {
            binding.etName.clearFocus()
            val name = binding.etName.text.toString()
            if (name.isBlank()) {
                showMessageByToast("Введите название")
                return@setOnClickListener
            }
            viewModel.saveClient(Client(name = name, id = 0))
            closeFragment()
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteClient()
            closeFragment()
        }

        viewModel = ViewModelProvider(this).get(ClientCreateEditViewModel::class.java)

        val clientId = initParams.id
        if (clientId != null) {
            viewModel.getClient(clientId)
        }

        viewModel.clientLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(client: Client?) {
        if (client != null) {
            binding.etName.setText(client.name)
        }
    }

    companion object {
        fun newInstance(initParams: ClientCreateEditFragmentInitParams) =
            ClientCreateEditFragment().provideInitParams(initParams) as ClientCreateEditFragment
    }
}
