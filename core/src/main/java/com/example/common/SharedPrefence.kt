package com.example.common

import android.content.Context
import android.util.Log

private const val SHARED_PREFERENCES_NAME = "MyAppPrefs"
const val SESSION_EVENTS = "isFirstRun"
const val SESSION_CATEGORY = "isFirstRun"



fun isFirstEnter(context: Context, name: String): Boolean {
    val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    val isFirstTime = sharedPreferences.getBoolean(name, true)

    sharedPreferences.edit().putBoolean(name, false).apply()
    Log.d("GetFirstEnter", "$name: $isFirstTime")
    return isFirstTime
}

fun cancelSession(context: Context) {
    val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean(SESSION_EVENTS, true).apply()
    sharedPreferences.edit().putBoolean(SESSION_CATEGORY, true).apply()
}