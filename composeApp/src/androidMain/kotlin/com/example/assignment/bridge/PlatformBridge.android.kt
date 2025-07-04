package com.example.assignment.bridge

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.assignment.AppClass
import com.example.assignment.bridge.system_bar_colors.AndroidSystemBarColors
import io.ktor.client.engine.okhttp.OkHttp

class AndroidPlatformBridge : PlatformBridge {

    override fun getDataStore(dataStoreFileName: String): DataStore<Preferences> {
        val context = AppClass.instance
        val file = context.filesDir.resolve(dataStoreFileName)
        return createDataStoreInCommon(producePath = { file.absolutePath })
    }

    override fun httpClientEngine() = OkHttp.create()

    override fun systemBarColors() = AndroidSystemBarColors()
}

actual val platformBridge: PlatformBridge = AndroidPlatformBridge()