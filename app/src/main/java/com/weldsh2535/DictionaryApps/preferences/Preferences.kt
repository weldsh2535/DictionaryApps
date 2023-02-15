package com.weldsh2535.DictionaryApps.preferences

import android.app.Application
import android.preference.PreferenceManager

class Preferences(app:Application) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(app)
    val theme = BetterLiveData(getListPreferenceValue(Theme))

    private fun getListPreferenceValue(info: ListInfo): String {
        return requireNotNull(preferences.getString(info.key, info.defaultValue))
    }
    companion object {
        @Volatile
        private var instance: Preferences? = null

        fun getInstance(app: Application): Preferences {
            return instance ?: synchronized(this) {
                val instance = Preferences(app)
                this.instance = instance
                return instance
            }
        }
    }
}