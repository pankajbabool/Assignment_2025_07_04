package com.example.assignment.bridge

import com.example.assignment.bridge.system_bar_colors.IosSystemBarColors
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

class IosPlatformBridge : PlatformBridge {

    @OptIn(ExperimentalForeignApi::class)
    override fun getDataStore(dataStoreFileName: String) = createDataStoreInCommon {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }

    override fun httpClientEngine() = Darwin.create()

    override fun systemBarColors() = IosSystemBarColors()
}

actual val platformBridge: PlatformBridge = IosPlatformBridge()