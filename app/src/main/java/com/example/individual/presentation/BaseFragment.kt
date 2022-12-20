package com.example.individual.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.individual.common.Navigator
import com.example.individual.utils.DialogUtils

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel
    private val loadingIndicator: LoadingIndicator by lazy { LoadingIndicator(requireActivity()) }

    protected open var navigator: Navigator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.messageByToastLiveData.observeForever { message ->
            showMessageByToast(message = message)
        }

        viewModel.isLoadingLiveData.observeForever { isLoading ->
            if (isLoading) {
                loadingIndicator.show()
            } else {
                loadingIndicator.dismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = context as Navigator
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                closeFragment()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    open fun closeFragment() {
        (requireActivity() as Navigator).exit(this)
    }

    override fun onDestroy() {
        navigator = null
        super.onDestroy()
    }

    fun showMessageByToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, duration).show()
    }

    fun showMessageByAlertDialog(
        title: String? = null,
        message: String? = null,
        positiveText: String? = "Ok",
        negativeText: String? = null,
        onPositiveButtonClick: (() -> Unit)? = null,
        onNegativeButtonClick: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        DialogUtils.showMessageByAlertDialog(
            context = this.requireContext(),
            title = title,
            message = message,
            positiveText = positiveText,
            negativeText = negativeText,
            onPositiveButtonClick = onPositiveButtonClick,
            onNegativeButtonClick = onNegativeButtonClick,
            onDismiss = onDismiss
        )
    }
}