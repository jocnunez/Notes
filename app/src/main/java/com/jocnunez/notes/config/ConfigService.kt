package com.jocnunez.notes.config

import android.content.Context
import androidx.preference.PreferenceManager


class ConfigService (val context: Context) {
    // Preferences Keys
    val defaultFileKey = "default_file_key"
    val isFirebaseActive = "is_firebase_active"

    fun getDefaultFileName():String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(defaultFileKey, "")
    }

    fun setDefaultFileName(name: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putString(defaultFileKey, name)
            apply()
        }
    }

    fun getFirebaseActive():Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(isFirebaseActive, false)
    }

    fun setFirebaseActive(isActive: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putBoolean(isFirebaseActive, isActive)
            apply()
        }
    }
}