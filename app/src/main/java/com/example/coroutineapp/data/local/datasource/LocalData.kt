package com.example.coroutineapp.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalData @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val LOGIN_TOKEN = stringPreferencesKey("login_token")

    val loginTokenFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            Timber.d("preferences :: ${preferences[LOGIN_TOKEN]}")
            preferences[LOGIN_TOKEN] ?: ""
        }

    suspend fun setLoginToken(token: String) {
        context.dataStore.edit { settings ->
            settings[LOGIN_TOKEN] = token
        }
    }
}