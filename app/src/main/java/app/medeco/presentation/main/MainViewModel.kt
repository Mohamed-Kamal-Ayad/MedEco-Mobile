package app.medeco.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.medeco.domain.model.preferences.booleanPreferencesKey
import app.medeco.domain.model.preferences.intPreferencesKey
import app.medeco.domain.model.preferences.stringPreferencesKey
import app.medeco.domain.use_case.preferences.GetEncryptedPreferenceUseCase
import app.medeco.domain.use_case.preferences.GetPreferenceUseCase
import app.medeco.domain.use_case.preferences.SavePreferenceUseCase
import app.medeco.presentation.common.Screen
import app.medeco.util.PrefsConstants.COMPLETED_ONBOARDING
import app.medeco.util.PrefsConstants.TOKEN_KEY
import app.medeco.util.PrefsConstants.USER_TYPE
import app.medeco.util.UserType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getPreference: GetPreferenceUseCase,
    private val savePreference: SavePreferenceUseCase,
    private val getEncryptedPreference: GetEncryptedPreferenceUseCase
): ViewModel() {

    var showSplashScreen = true
        private set

    private val mainScreenChannel = Channel<MainEvent>()
    val mainActivityEventFlow = mainScreenChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val completedOnboarding = getPreference(booleanPreferencesKey(COMPLETED_ONBOARDING), false).first()
            if (completedOnboarding) {
                val token = getEncryptedPreference(stringPreferencesKey(TOKEN_KEY), "")
                if (token.isBlank()) {
                    mainScreenChannel.send(MainEvent.Navigate(Screen.AccountTypeScreen))
                } else {
                    val type = getPreference(intPreferencesKey(USER_TYPE), UserType.USER.prefsValue).first()
                    mainScreenChannel.send(
                        MainEvent.Navigate(
                            if (type == UserType.USER.prefsValue) Screen.UserMainScreen
                            else Screen.PharmacyMainScreen
                        )
                    )
                }
            }
            delay(400)
            showSplashScreen = false
        }
    }

    fun onBoardingFinished() = viewModelScope.launch {
        savePreference(booleanPreferencesKey(COMPLETED_ONBOARDING), true)
    }


}