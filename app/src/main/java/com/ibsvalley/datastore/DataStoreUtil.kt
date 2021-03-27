package com.ibsvalley.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.security.AccessController.getContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtil {

    suspend fun Context.write(key: String, value: String) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value
        }
    }

    suspend fun Context.read(key: String, defaultValue: String): String {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)] ?: defaultValue
        }.first().toString()
    }

    suspend fun Context.write(key: String, value: Int) {
        dataStore.edit { settings ->
            settings[intPreferencesKey(key)] = value
        }
    }

    suspend fun Context.read(key: String, defaultValue: Int): Int {
        return dataStore.data.map { settings ->
            settings[intPreferencesKey(key)] ?: defaultValue
        }.first().toInt()
    }

    suspend fun Context.write(key: String, value: Double) {
        dataStore.edit { settings ->
            settings[doublePreferencesKey(key)] = value
        }
    }

    suspend fun Context.read(key: String, defaultValue: Double): Double {
        return dataStore.data.map { settings ->
            settings[doublePreferencesKey(key)] ?: defaultValue
        }.first().toDouble()

    }

    suspend fun Context.write(key: String, value: Boolean) {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(key)] = value
        }
    }

    suspend fun Context.read(key: String, defaultValue: Boolean): Boolean {
        return dataStore.data.map { settings ->
            settings[booleanPreferencesKey(key)] ?: defaultValue
        }.first()

    }

    suspend fun Context.clear() {
        dataStore.edit { it.clear() }
    }

}

