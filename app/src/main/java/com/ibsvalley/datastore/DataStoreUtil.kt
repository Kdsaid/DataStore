package com.ibsvalley.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first

class DataStoreUtil private constructor() {
    private var dataStore: DataStore<Preferences>


    suspend fun write(key: String, value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun read(key: String,defaultValue:String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        if (preferences[dataStoreKey]!=null)
        return preferences[dataStoreKey]
        return defaultValue
    }

    suspend fun read(key: String, defaultValue: Int ): Int? {
        val dataStoreKey = preferencesKey<Int>(key)
        val preferences = dataStore.data.first()
        if (preferences[dataStoreKey] != null) {
            return preferences[dataStoreKey]
        }
        return defaultValue
    }

    suspend fun write(key: String, value: Int) {
        val dataStoreKey = preferencesKey<Int>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun read(key: String, defaultValue: Double ): Double? {
        val dataStoreKey = preferencesKey<Double>(key)
        val preferences = dataStore.data.first()
        if (preferences[dataStoreKey] != null) {
            return preferences[dataStoreKey]
        }
        return defaultValue
    }

    suspend fun write(key: String, value: Double) {
        val dataStoreKey = preferencesKey<Double>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun read(key: String, defaultValue: Boolean ): Boolean? {
        val dataStoreKey = preferencesKey<Boolean>(key)
        val preferences = dataStore.data.first()
        if (preferences[dataStoreKey] != null) {
            return preferences[dataStoreKey]
        }
        return defaultValue
    }

    suspend fun write(key: String, value: Boolean) {
        val dataStoreKey = preferencesKey<Boolean>(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }


    companion object {
        private var context: Context? = null

        private var instance: DataStoreUtil? = null
        private var dataStoreName: String? = null

        fun getDataStore(context: Context): DataStoreUtil? {
            Companion.context = context
            dataStoreName = context.packageName
            if (instance == null) {
                instance = DataStoreUtil()
            }
            return instance
        }
    }


    init {
        dataStore = dataStoreName?.let { context!!.createDataStore(it) }!!


    }
}