package app.medeco.presentation.main

import app.medeco.presentation.common.Screen

sealed class MainEvent {
    data class Navigate(val screen: Screen): MainEvent()
}
