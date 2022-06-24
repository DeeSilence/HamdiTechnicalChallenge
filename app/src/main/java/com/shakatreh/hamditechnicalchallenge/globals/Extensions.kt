package com.shakatreh.hamditechnicalchallenge.globals

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*


fun String.validEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.validPassword(): Boolean {
    return this.length in 6..12
}

fun Long.validAge(): Boolean {
    return getAgeInYears() in 18..99
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Long.displayDate(): String {
    if (this < 1)
        return ""
    val date = Date(this)
    val format = SimpleDateFormat("dd/M/yyyy")
    return format.format(date)
}

fun Long.getAgeInYears(): Int {
    val dob = Calendar.getInstance()
    dob.time = Date(this)
    val today = Calendar.getInstance()

    var age = today.get(Calendar.YEAR) - dob[Calendar.YEAR]
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age--
    }

    return age
}