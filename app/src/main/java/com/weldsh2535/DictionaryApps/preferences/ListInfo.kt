package com.weldsh2535.DictionaryApps.preferences

import android.content.Context
import com.weldsh2535.DictionaryApps.R

sealed interface ListInfo {

    val title: Int

    val key: String
    val defaultValue: String

    fun getEntryValues(): Array<String>

    fun getEntries(context: Context): Array<String>

    fun convertToValues(ids: Array<Int>, context: Context): Array<String> {
        return Array(ids.size) { index ->
            val id = ids[index]
            context.getString(id)
        }
    }
}

object View : ListInfo {
    const val list = "list"
    const val grid = "grid"

    override val title = R.string.view
    override val key = "view"
    override val defaultValue = list

    override fun getEntryValues() = arrayOf(list, grid)

    override fun getEntries(context: Context): Array<String> {
        val ids = arrayOf(R.string.list, R.string.grid)
        return convertToValues(ids, context)
    }
}

object Theme : ListInfo {
    const val dark = "dark"
    const val light = "light"
    const val followSystem = "followSystem"

    override val title = R.string.theme
    override val key = "theme"
    override val defaultValue = followSystem

    override fun getEntryValues() = arrayOf(dark, light, followSystem)

    override fun getEntries(context: Context): Array<String> {
        val ids = arrayOf(R.string.dark, R.string.light, R.string.follow_system)
        return convertToValues(ids, context)
    }
}

