package com.example.fampayproject.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "sharedPref"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private val KEY = "KEY"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
        println("pref initialised")
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var removedCardsJson: String?
        get() = preferences.getString(KEY, "")
        set(value) = preferences.edit {
            it.putString(KEY, value)
        }
}
