package com.farahaniconsulting.flicko.ui.helper.ui

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideSoftKeyboard() {
    if (currentFocus == null){
        return
    }
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.windowToken?.let {
        inputMethodManager.hideSoftInputFromWindow(it, 0)
    }

}