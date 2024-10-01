package com.example.newsapp.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manger.LocalUserManger
import com.example.newsapp.ui.theme.utils.Constants
import com.example.newsapp.ui.theme.utils.Constants.APP_ENTRY
import com.example.newsapp.ui.theme.utils.Constants.USER_SETTING
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


///data store preference is just a jetpack library that allow us to save a key value locally on device

//shared preference is  a api to save  key value and now recommanded by android
class LocalUserMangerIMP(
    private val context : Context

) : LocalUserManger{
    override suspend fun saveAppEntry() {
      context.datastore.edit { settings ->
          settings[PreferenceKeys.APP_ENTRY] = true
      }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map {preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false

        }
    }
}


//extensions value
private val Context.datastore:DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)


///prefernecess keys

private object  PreferenceKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}