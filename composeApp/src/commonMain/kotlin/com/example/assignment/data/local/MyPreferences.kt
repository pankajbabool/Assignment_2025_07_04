package com.example.assignment.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.assignment.bridge.platformBridge
import kotlinx.coroutines.flow.first

object MyPreferences {
    val dataStore by lazy { platformBridge.getDataStore() }

    suspend inline fun <reified T> getValue(key: String) = when (T::class) {
        Boolean::class -> dataStore.data.first()[booleanPreferencesKey(key)] ?: false  as T
        String::class -> dataStore.data.first()[stringPreferencesKey(key)] ?: "" as T
        Double::class -> dataStore.data.first()[doublePreferencesKey(key)] ?: 0.0 as T
        Float::class -> dataStore.data.first()[floatPreferencesKey(key)] ?: 0.0f as T
        Long::class -> dataStore.data.first()[longPreferencesKey(key)] ?: 0L as T
        Int::class -> dataStore.data.first()[intPreferencesKey(key)] ?: -1 as T
        else -> throw IllegalArgumentException("Unsupported data type")
    }

    suspend inline fun <reified T> setValue(value: T, key: String) {
        when (T::class) {
            Boolean::class -> dataStore.edit { it[booleanPreferencesKey(key)] = value as Boolean }
            String::class -> dataStore.edit { it[stringPreferencesKey(key)] = value as String }
            Double::class -> dataStore.edit { it[doublePreferencesKey(key)] = value as Double }
            Float::class -> dataStore.edit { it[floatPreferencesKey(key)] = value as Float }
            Long::class -> dataStore.edit { it[longPreferencesKey(key)] = value as Long }
            Int::class -> dataStore.edit { it[intPreferencesKey(key)] = value as Int }
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }
}