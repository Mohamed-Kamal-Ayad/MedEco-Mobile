package app.medeco.presentation.pharmacy.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.data.remote.pharmacy.model.NetworkRequest
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.pharmacies.ApproveNetworkRequestUseCase
import app.medeco.domain.use_case.pharmacies.GetAllBranchesUseCase
import app.medeco.domain.use_case.pharmacies.GetAllNetworkRequestsUseCase
import app.medeco.domain.use_case.pharmacies.MakeNetworkRequestUseCase
import app.medeco.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import app.medeco.util.PrefsConstants.TOKEN_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PharmacyNetworkViewModel(
    private val makeRequest: MakeNetworkRequestUseCase,
    private val getAllBranches: GetAllBranchesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PharmacyNetworkUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val branches = getAllBranches()
                _uiState.update { it.copy(loading = false, branches = branches) }
            } catch (e: Exception) {
                _uiState.update { it.copy(loading = false, error = "Error getting requests") }
            }
        }
    }


    fun onEvent(event: PharmacyNetworkEvent) {
        when (event) {
            is PharmacyNetworkEvent.MakeRequest -> viewModelScope.launch {
                _uiState.update { it.copy(loading = true) }
                try {
                    makeRequest(NetworkRequest(event.description, event.branchId))
                    _uiState.update { it.copy(navigateUp = true, loading = false) }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = "Error making request"
                        )
                    }
                }
            }

//            is PharmacyNetworkEvent.ApproveRequest -> viewModelScope.launch {
//                _uiState.update { it.copy(loading = true) }
//                try {
//                    approveRequest(event.request)
//                    val newRequests = _uiState.value.requests - event.request
//                    _uiState.update { it.copy(loading = false, requests = newRequests) }
//                } catch (e: Exception) {
//                    _uiState.update {
//                        it.copy(
//                            loading = false,
//                            error = "Error approving request"
//                        )
//                    }
//                }
//
//            }

            else -> Unit
        }
    }
}