package app.medeco.presentation.pharmacy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.preferences.intPreferencesKey
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.pharmacies.GetAllNetworkRequestsUseCase
import app.medeco.domain.use_case.preferences.GetPreferenceUseCase
import app.medeco.util.PrefsConstants.USER_ID
import app.medeco.util.PrefsConstants.USER_IMAGE_KEY
import app.medeco.util.PrefsConstants.USER_NAME_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PharmacyHomeViewModel(
    private val getPreference: GetPreferenceUseCase,
    private val getNetworkRequests: GetAllNetworkRequestsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PharmacyHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val logo = getPreference(stringPreferencesKey(USER_IMAGE_KEY), "").first()
            val name = getPreference(stringPreferencesKey(USER_NAME_KEY), "").first()
            val id = getPreference(intPreferencesKey(USER_ID), 0).first()
            _uiState.update {
                it.copy(
                    id = id,
                    name = name,
                    logo = logo,
                )
            }
            try {
                val networkRequests = getNetworkRequests()
                if (networkRequests.isNotEmpty()) {
                    _uiState.update {
                        it.copy(hasNotifications = true)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.localizedMessage)
                }
            }
        }
    }

}