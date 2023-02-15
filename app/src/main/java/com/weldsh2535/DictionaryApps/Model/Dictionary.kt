package com.weldsh2535.DictionaryApps.Model

class Dictionary(
    var id: Int,
    var geez: String,
    var amharic: String,
    var is_favourite: Int,
    var isExpandable:Boolean = false) {

    override fun toString(): String {
        return "Amharic text=$amharic \n Geez text = $geez"
    }
}