package app.medeco.domain.use_case.preferences

import app.medeco.domain.model.preferences.PrefsKey
import app.medeco.domain.repository.preferences.PreferencesRepository
import org.koin.core.annotation.Single

@Single
class SavePreferenceUseCase(
    private val repository: PreferencesRepository
) {
    suspend operator fun <T> invoke(key: PrefsKey, value: T) = repository.savePreference(key, value)
}