package com.example.newproject.util
import android.content.Context
import com.example.newproject.App

object SharedPreferenceUtil {
    private val PREF_NAME = "app_preference"
    private val PREF_TOKEN = "token"

    fun saveToken(token: String?) {
        App.instance.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().putString(
            PREF_TOKEN, token
        ).commit()
    }


    fun getToken(): String? =
        App.instance.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(
            PREF_TOKEN, null
        )


}