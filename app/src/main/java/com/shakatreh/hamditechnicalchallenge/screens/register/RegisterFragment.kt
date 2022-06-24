package com.shakatreh.hamditechnicalchallenge.screens.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.textfield.TextInputLayout
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.databinding.FragmentRegisterBinding
import com.shakatreh.hamditechnicalchallenge.globals.BaseFragment
import com.shakatreh.hamditechnicalchallenge.globals.validAge
import com.shakatreh.hamditechnicalchallenge.screens.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(), MaterialPickerOnPositiveButtonClickListener<Long> {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private val datePicker by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.date_of_birth))
            .build()
    }

    override fun setTitle(): String? {
        return context?.getString(R.string.register)
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            showSnackBar(it)
        }
        viewModel.progressStatus.observe(viewLifecycleOwner) {
            if (it) showProgress() else hideProgress()
        }
        viewModel.showDatePicker.observe(viewLifecycleOwner) {
            if (it) openDatePicker()
        }
        viewModel.navToHome.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.navToHome.value = false
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
            }
        }
    }

    private fun openDatePicker() {
        if (datePicker.isAdded)
            return
        activity?.supportFragmentManager?.let {
            datePicker.addOnPositiveButtonClickListener(this)
            datePicker.show(it, DATE_PICKER)
        }
    }


    override fun onPositiveButtonClick(selection: Long) {
        viewModel.dob.value = selection
        if (selection.validAge())
            binding.tilDob.error = null
        else
            binding.tilDob.error = getString(R.string.incorrect_dob)
        viewModel.onDobChanged(selection)
    }

    companion object {
        const val DATE_PICKER = "DATE_PICKER"
    }
}