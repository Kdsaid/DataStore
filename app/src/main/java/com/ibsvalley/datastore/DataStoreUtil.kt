package com.ibsvalley.datastore
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreUtil  {
    private lateinit var dataStore: DataStore<Preferences>
    fun Context.getDataStore(): DataStoreUtil? {
        dataStoreName = packageName
        if (instance == null) {
            synchronized(DataStoreUtil::class.java) {
                instance = DataStoreUtil
            }
        }
        return instance
    }
    suspend fun write(key: String, value: String) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = value
        }
    }

    suspend fun read(key: String, defaultValue: String): String {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)] ?: defaultValue
        }.first().toString()
    }

    suspend fun write(key: String, value: Int) {
        dataStore.edit { settings ->
            settings[intPreferencesKey(key)] = value
        }
    }
    suspend fun read(key: String, defaultValue: Int): Int {
        return dataStore.data.map { settings ->
            settings[intPreferencesKey(key)] ?: defaultValue
        }.first().toInt()
    }


    suspend fun write(key: String, value: Double) {
        dataStore.edit { settings ->
            settings[doublePreferencesKey(key)] = value
        }
    }
    suspend fun read(key: String, defaultValue: Double): Double {
        return dataStore.data.map { settings ->
            settings[doublePreferencesKey(key)] ?: defaultValue
        }.first().toDouble()

    }
    suspend fun write(key: String, value: Boolean) {
        dataStore.edit { settings ->
            settings[booleanPreferencesKey(key)] = value
        }
    }
    suspend fun read(key: String, defaultValue: Boolean): Boolean {
        return dataStore.data.map { settings ->
            settings[booleanPreferencesKey(key)] ?: defaultValue
        }.first()

    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
    private var instance: DataStoreUtil? = null
    private var dataStoreName: String? = null




}