package com.gamerguide.android.starterapp.helpers

import android.app.Activity
import android.content.Context

object SimpleStorage {

    fun fromStorage(activity: Activity, key: String, defaultValue: Int): Int {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        return sharedPref.getInt(key, defaultValue)
    }

    fun fromStorage(activity: Activity, key: String, defaultValue: Float): Float {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        return sharedPref.getFloat(key, defaultValue)
    }

    fun fromStorage(activity: Activity, key: String, defaultValue: String): String? {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        return sharedPref.getString(key, defaultValue)
    }

    fun fromStorage(activity: Activity, key: String, defaultValue: Boolean): Boolean {

        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        return sharedPref.getBoolean(key, defaultValue)
    }

    fun intoStorage(activity: Activity, key: String, value: Int) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

    fun intoStorage(activity: Activity, key: String, value: Float) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putFloat(key, value)
            apply()
        }
    }

    fun intoStorage(activity: Activity, key: String, value: Boolean) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }


    fun intoStorage(activity: Activity, key: String, value: String) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

}