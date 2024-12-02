package app.medeco.domain.repository.preferences

import app.medeco.domain.model.preferences.PrefsKey
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    suspend fun <T> savePreference(key: PrefsKey, value: T)

    fun <T> getPreference(key: PrefsKey, defaultValue: T): Flow<T>
}