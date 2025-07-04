package com.example.assignment.extensions

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toJsonString(): String? {
    return runCatching { Json.encodeToString(this) }
        .onFailure { it.printStackTrace() }
        .getOrNull()
}

inline fun <reified T> String.toMyDataClass(): T? {
    return runCatching { Json.decodeFromString<T>(this) }
        .onFailure { it.printStackTrace() }
        .getOrNull()
}