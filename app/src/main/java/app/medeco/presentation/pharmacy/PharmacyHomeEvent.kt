package app.medeco.presentation.pharmacy

sealed class PharmacyHomeEvent {
    data object Refresh : PharmacyHomeEvent()
}