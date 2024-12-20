package app.medeco.presentation.user.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.preferences.GetPreferenceUseCase
import app.medeco.domain.use_case.preferences.SaveEncryptedPreferenceUseCase
import app.medeco.domain.use_case.preferences.SavePreferenceUseCase
import app.medeco.util.PrefsConstants.TOKEN_KEY
import app.medeco.util.PrefsConstants.USER_NAME_KEY
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserProfileViewModel(
    private val saveEncryptedPreference: SaveEncryptedPreferenceUseCase,
    private val savePreference: SavePreferenceUseCase,
    getPreference: GetPreferenceUseCase
) : ViewModel() {

    var state by mutableStateOf(UserProfileUiState())
        private set

    init {
        getPreference(stringPreferencesKey(USER_NAME_KEY), "")
            .onEach {
                state = state.copy(fullName = it)
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, "")
    }

    fun onEvent(event: UserProfileEvent) {
        when (event) {
            is UserProfileEvent.SignOut -> viewModelScope.launch {
                saveEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
                savePreference(stringPreferencesKey(USER_NAME_KEY), "")
                state = state.copy(signedOut = true)
            }
            else -> Unit
        }
    }

}