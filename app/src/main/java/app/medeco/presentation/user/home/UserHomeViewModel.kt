package app.medeco.presentation.user.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.preferences.GetPreferenceUseCase
import app.medeco.domain.use_case.user.GetUserPointsUseCase
import app.medeco.util.PrefsConstants.USER_NAME_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserHomeViewModel(
    private val getPreference: GetPreferenceUseCase,
    private val getPoints: GetUserPointsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserHomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                val name = getPreference(stringPreferencesKey(USER_NAME_KEY), "").first()
                _uiState.update {
                    it.copy(
                        userName = name
                    )
                }
            }
            launch {
                try {
                    val points = getPoints()
                    _uiState.update {
                        it.copy(
                            points = points
                        )
                    }
                } catch (_: Exception) {}
            }

        }
    }
}