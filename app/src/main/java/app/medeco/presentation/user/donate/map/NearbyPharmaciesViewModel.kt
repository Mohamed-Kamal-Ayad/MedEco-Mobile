package app.medeco.presentation.user.donate.map

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.pharmacy.Pharmacy
import app.medeco.domain.model.pharmacy.PharmacyBranch
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.user.GetAllPharmaciesUseCase
import app.medeco.domain.use_case.user.GetUserLocationUseCase
import app.medeco.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import app.medeco.domain.use_case.user.UrlToBitmapUseCase
import app.medeco.util.PrefsConstants.TOKEN_KEY
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NearbyPharmaciesViewModel(
    private val getUserLocation: GetUserLocationUseCase,
    private val getPharmacies: GetAllPharmaciesUseCase,
    private val bitMapCreator: UrlToBitmapUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NearbyPharmaciesUiState())
    val uiState = _uiState.asStateFlow()
    private val urlToBitMap = HashMap<String?, ImageBitmap?>().apply {
        this[null] = null
    }

    init {
        viewModelScope.launch {
            val location = async { getUserLocation() }
            val pharmacies = async {
                getPharmacies()
//                listOf(
//                    Pharmacy(
//                        hotline = "12345",
//                        id = 7635,
//                        name = "El Ezaby pharmacy",
//                        logo = "https://binvestmentsegypt.com/wp-content/uploads/2023/11/el-ezaby-logo.jpg",
//                        branches = listOf(
//                            PharmacyBranch(
//                                id = 0,
//                                phone = "12345",
//                                lat = 31.045705,
//                                lng = 31.354876,
//                                address = "Mansoura, Al Gemhoureya Street"
//                            )
//                        ),
//                    ),
//                    Pharmacy(
//                        hotline = "3433",
//                        id = 7635,
//                        name = "Al-Tarshouby pharmacy",
//                        logo = "https://i.pinimg.com/280x280_RS/a6/46/9e/a6469e3a4906faacbb2b5d0f44b2bca1.jpg",
//                        branches = listOf(
//                            PharmacyBranch(
//                                id = 3,
//                                phone = "16130",
//                                lat = 31.043228,
//                                lng = 31.357704,
//                                address = "Mansoura University"
//                            )
//                        ),
//                    )
//                )
            }
            _uiState.update { state ->
                try {
                    state.copy(
                        userLocation = location.await() ?: state.userLocation,
                        pharmacies = pharmacies.await().map { pharmacy ->
                            pharmacy.copy(
                                branches = pharmacy.branches?.filter { it.lat != null && it.lng != null }
                            ).also {
                                it.logoBitmap = urlToBitMap.getOrPut(it.logo) {
                                    bitMapCreator(it.logo ?: "")?.asImageBitmap()
                                }
                            }
                        }
                    )
                } catch (e: Exception) {
                    state.copy(error = e.localizedMessage)
                }
            }
        }
    }


}