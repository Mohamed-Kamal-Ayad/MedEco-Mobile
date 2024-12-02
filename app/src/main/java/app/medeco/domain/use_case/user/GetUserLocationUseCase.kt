package app.medeco.domain.use_case.user

import app.medeco.data.remote.location.LocationManager
import org.koin.core.annotation.Single

@Single
class GetUserLocationUseCase(
    private val locationManager: LocationManager
) {
    suspend operator fun invoke() = locationManager.getUserLocation()
}