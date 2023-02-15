package com.weldsh2535.DictionaryApps.preferences

import androidx.lifecycle.MutableLiveData

class BetterLiveData<T>(value:T) : MutableLiveData<T>(value) {
    override fun getValue(): T? {
        return requireNotNull( super.getValue())
    }
}