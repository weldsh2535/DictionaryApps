package com.weldsh2535.DictionaryApps.App

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.weldsh2535.DictionaryApps.db.DbHelper
import com.weldsh2535.DictionaryApps.preferences.Preferences
import com.weldsh2535.DictionaryApps.preferences.Theme


class App: Application()   {
    override fun onCreate() {
        super.onCreate()
        DbHelper.init(this)

        val preferences = Preferences.getInstance(this)
        preferences.theme.observeForever { theme ->
            when(theme) {
                Theme.dark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Theme.light -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Theme.followSystem -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}