package app.medeco.presentation.user.donate.map

import com.google.android.gms.maps.model.LatLng
import app.medeco.domain.model.pharmacy.Pharmacy

data class NearbyPharmaciesUiState(
    val pharmacies: List<Pharmacy> = emptyList(),
    val userLocation: LatLng = LatLng(31.044367, 31.354581),
    val error: String? = null
)
