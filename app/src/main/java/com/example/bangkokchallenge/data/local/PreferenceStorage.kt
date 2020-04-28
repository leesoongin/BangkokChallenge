package com.example.bangkokchallenge.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by choejun-yeong on 04/04/2020.
 */

interface PreferenceStorage{
    var userId : String?
    var nickname: String?
    var userProfileImagePath:String?
    var userToken : String?
}


class SharedPreferenceStorage  constructor(context: Context) : PreferenceStorage {

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    companion object { //key값
        const val PREFS_NAME = "app_prefs_name"
        const val USER_ID = "user_id"
        const val NICNAME = "user_nickname"
        const val USER_PROFILEIMAGE_PATH="user_profileimage_path"
        const val USER_TOKEN = "user_token"
    }

    override var userId by StringPreference(prefs, USER_ID, null)
    override var nickname by StringPreference(prefs, NICNAME,null)
    override var userProfileImagePath by StringPreference(prefs, USER_PROFILEIMAGE_PATH,null)
    override var userToken by StringPreference(prefs, USER_TOKEN, null)

}


class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String?
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return preferences.value.getString(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.value.edit { putString(name, value) }
    }
}


class BooleanPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return preferences.value.getBoolean(name, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        preferences.value.edit { putBoolean(name, value) }
    }
}
