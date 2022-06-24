package com.shakatreh.hamditechnicalchallenge.screens.register

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepository
import com.shakatreh.hamditechnicalchallenge.globals.hideKeyboard
import com.shakatreh.hamditechnicalchallenge.globals.validAge
import com.shakatreh.hamditechnicalchallenge.globals.validEmail
import com.shakatreh.hamditechnicalchallenge.globals.validPassword
import com.shakatreh.hamditechnicalchallenge.repos.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    private val usersRepo: UsersRepo
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val dob = MutableLiveData<Long>()
    val loginButtonEnabled = MutableLiveData<Boolean>()
    val progressStatus = MutableLiveData<Boolean>()
    val showDatePicker = MutableLiveData<Boolean>()
    val navToHome = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    val onEmailFocusChangeListener =
        View.OnFocusChangeListener { view, isFocused ->
            if (isFocused)
                (view.parent.parent as TextInputLayout?)?.error = null
            else {
                if (email.value?.validEmail() == true)
                    (view.parent.parent as TextInputLayout?)?.error = null
                else
                    (view.parent.parent as TextInputLayout?)?.error =
                        commonRepository.getContext().getString(R.string.incorrect_email)

            }
            loginButtonEnabled.value =
                email.value?.validEmail() == true && password.value?.validPassword() == true
        }

    val onPasswordFocusChangeListener =
        View.OnFocusChangeListener { view, isFocused ->
            if (isFocused)
                (view.parent.parent as TextInputLayout?)?.error = null
            else {
                if (password.value?.validPassword() == true)
                    (view.parent.parent as TextInputLayout?)?.error = null
                else
                    (view.parent.parent as TextInputLayout?)?.error =
                        commonRepository.getContext().getString(R.string.incorrect_password)

            }
            loginButtonEnabled.value =
                email.value?.validEmail() == true && password.value?.validPassword() == true
        }


    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        loginButtonEnabled.value =
            s.toString()
                .validEmail() == true && password.value?.validPassword() == true && dob.value?.validAge() == true
    }

    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        loginButtonEnabled.value =
            email.value?.validEmail() == true && s.toString()
                .validPassword() == true && dob.value?.validAge() == true
    }

    fun onDobChanged(newValue: Long) {
        loginButtonEnabled.value =
            email.value?.validEmail() == true && password.value?.validPassword() == true && newValue.validAge() == true
    }

    fun register(view: View) {
        commonRepository.getContext().hideKeyboard(view)
        progressStatus.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val status =
                    usersRepo.insertUser(
                        email = email.value ?: "",
                        password = password.value ?: "",
                        dob = dob.value ?: 0L,
                    )
                progressStatus.postValue(false)
                navToHome.postValue(true)
                errorMsg.postValue(
                    commonRepository.getContext().getString(R.string.user_added_successfully)
                )

            } catch (e: Exception) {
                progressStatus.postValue(false)
                errorMsg.postValue(
                    commonRepository.getContext().getString(R.string.user_already_registred)
                )
            }
        }
    }

    fun openDatePicker(view: View) {
        showDatePicker.value = true
    }
}