package app.medeco.domain.repository.preferences

import app.medeco.domain.model.preferences.PrefsKey

interface EncryptedPreferencesRepository {

    suspend fun <T> savePreference(key: PrefsKey, value: T)

    suspend fun <T> getPreference(key: PrefsKey, defaultValue: T): T
}