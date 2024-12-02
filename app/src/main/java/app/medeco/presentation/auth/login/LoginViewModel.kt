package app.medeco.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.preferences.intPreferencesKey
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.auth.LoginUseCase
import app.medeco.domain.use_case.preferences.SaveEncryptedPreferenceUseCase
import app.medeco.domain.use_case.preferences.SavePreferenceUseCase
import app.medeco.util.PrefsConstants.TOKEN_KEY
import app.medeco.util.PrefsConstants.USER_ID
import app.medeco.util.PrefsConstants.USER_IMAGE_KEY
import app.medeco.util.PrefsConstants.USER_NAME_KEY
import app.medeco.util.PrefsConstants.USER_TYPE
import app.medeco.util.UserType
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val login: LoginUseCase,
    private val saveEncryptedPreference: SaveEncryptedPreferenceUseCase,
    private val savePreference: SavePreferenceUseCase,
    userType: UserType
) : ViewModel() {

    var state by mutableStateOf(LoginUiState(userType = userType))
        private set

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.Login -> viewModelScope.launch {
                state = state.copy(loading = true, error = null)
                try {
                    val response = login(event.loginData)
                    saveEncryptedPreference(stringPreferencesKey(TOKEN_KEY), response.token)
                    if (state.userType == UserType.USER) {
                        response.user?.let {
                            savePreference(stringPreferencesKey(USER_NAME_KEY), it.name)
                            savePreference(intPreferencesKey(USER_TYPE), event.loginData.type.prefsValue)
                            savePreference(intPreferencesKey(USER_ID), it.id)
                            it.avatar?.let { avatar ->
                                savePreference(stringPreferencesKey(USER_IMAGE_KEY), avatar)
                            }
                        }
                    } else {
                        response.pharmacy?.let {
                            savePreference(stringPreferencesKey(USER_NAME_KEY), it.name)
                            savePreference(intPreferencesKey(USER_TYPE), event.loginData.type.prefsValue)
                            savePreference(intPreferencesKey(USER_ID), it.id)
                            it.logo?.let { avatar ->
                                savePreference(stringPreferencesKey(USER_IMAGE_KEY), avatar)
                            }
                        }
                    }
                    state = state.copy(loading = false, done = true)
                } catch (e: Exception) {
                    e.printStackTrace()
                    state = state.copy(loading = false, error = e.localizedMessage)
                }
            }
            else -> Unit
        }
    }

}