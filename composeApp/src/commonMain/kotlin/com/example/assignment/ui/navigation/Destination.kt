package com.example.assignment.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Splash : Destination()

    @Serializable
    data object Weather : Destination()

}