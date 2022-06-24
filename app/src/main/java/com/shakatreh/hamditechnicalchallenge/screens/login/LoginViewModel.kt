package com.shakatreh.hamditechnicalchallenge.screens.login

import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputLayout
import com.shakatreh.hamditechnicalchallenge.R
import com.shakatreh.hamditechnicalchallenge.di.common.CommonRepository
import com.shakatreh.hamditechnicalchallenge.globals.hideKeyboard
import com.shakatreh.hamditechnicalchallenge.globals.validEmail
import com.shakatreh.hamditechnicalchallenge.globals.validPassword
import com.shakatreh.hamditechnicalchallenge.repos.UsersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val commonRepository: CommonRepository,
    private val usersRepo: UsersRepo
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val loginButtonEnabled = MutableLiveData<Boolean>()
    val progressStatus = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()
    val navigateToRegisterFragment = MutableLiveData<Boolean>()
    val navigateToHomeFragment = MutableLiveData<Boolean>()

    val onEmailFocusChangeListener =
        OnFocusChangeListener { view, isFocused ->
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
        OnFocusChangeListener { view, isFocused ->
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
            s.toString().validEmail() == true && password.value?.validPassword() == true
    }

    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        loginButtonEnabled.value =
            email.value?.validEmail() == true && s.toString().validPassword() == true
    }

    fun getUser(view: View) {
        commonRepository.getContext().hideKeyboard(view)
        progressStatus.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user =
                    usersRepo.getUser(
                        email = email.value ?: "",
                        password = password.value ?: "",
                    )
                progressStatus.postValue(false)
                user?.let {
                    navigateToHomeFragment.postValue(true)
                } ?: errorMsg.postValue(
                    commonRepository.getContext().getString(R.string.user_was_not_found)
                )
            } catch (e: Exception) {
                progressStatus.postValue(false)
                errorMsg.postValue(e.message)
            }
        }
    }

    fun goToRegisterPage(view: View) {
        navigateToRegisterFragment.value = true
    }
}