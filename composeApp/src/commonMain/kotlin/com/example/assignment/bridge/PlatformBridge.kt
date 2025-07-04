package com.example.assignment.bridge

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.example.assignment.bridge.system_bar_colors.SystemBarColors
import io.ktor.client.engine.HttpClientEngine
import okio.Path.Companion.toPath

fun createDataStoreInCommon(producePath: () -> String) = PreferenceDataStoreFactory.createWithPath { producePath().toPath() }

interface PlatformBridge {

    fun getDataStore(dataStoreFileName: String = "dice.preferences_pb"): DataStore<Preferences>

    fun httpClientEngine(): HttpClientEngine

    fun systemBarColors(): SystemBarColors
}

expect val platformBridge: PlatformBridge