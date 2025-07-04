package com.example.assignment.data.local

import kotlinx.coroutines.runBlocking

var localIsRememberMeChecked : Boolean
    get() = runBlocking { MyPreferences.getValue<Boolean>("localIsRememberMeChecked") as Boolean }
    set(value) = runBlocking { MyPreferences.setValue<Boolean>(value, "localIsRememberMeChecked") }

var localAuthorizationToken : String
    get() = runBlocking { MyPreferences.getValue<String>("localAuthorizationToken") as String }
    set(value) = runBlocking { MyPreferences.setValue<String>(value, "localAuthorizationToken") }