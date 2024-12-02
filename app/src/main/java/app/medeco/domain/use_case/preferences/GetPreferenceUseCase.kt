package app.medeco.domain.use_case.preferences

import app.medeco.domain.model.preferences.PrefsKey
import app.medeco.domain.repository.preferences.PreferencesRepository
import org.koin.core.annotation.Single

@Single
class GetPreferenceUseCase(
    private val repository: PreferencesRepository
) {
    operator fun <T> invoke(key: PrefsKey, defaultValue: T) = repository.getPreference(key, defaultValue)
}